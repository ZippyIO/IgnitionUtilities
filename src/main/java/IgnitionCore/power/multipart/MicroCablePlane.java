package IgnitionCore.power.multipart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import IgnitionCore.misc.IGLNet_Utils;
import IgnitionCore.misc.IGL_Utils;
import IgnitionCore.misc.IUpdatedEntity;
import IgnitionCore.power.CableType;
import IgnitionCore.power.IPowerConductor;
import IgnitionCore.power.PowerInterface;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.PartMap;
import codechicken.multipart.TFacePart;
import codechicken.multipart.TMultiPart;

public class MicroCablePlane extends TMultiPart implements IPowerConductor, JNormalOcclusion, TFacePart, IUpdatedEntity{

	public PowerInterface cond = null;
	public Map<ForgeDirection,Boolean> conn = new HashMap<ForgeDirection,Boolean>();

	//boxes
	public static Cuboid6[] boundingBoxes = new Cuboid6[6];

	static {
		double w = 0.125;
		float h = 0.0625f*3;
		boundingBoxes[0] = new Cuboid6(0.5-w, 0,  0.5-w, 0.5+w, h, 0.5+w);	//base
		boundingBoxes[2] = new Cuboid6(0.5-w, 0,  0,     0.5+w, h, 0.5-w);	//north 2
		boundingBoxes[3] = new Cuboid6(0.5-w, 0,  0.5+w, 0.5+w, h, 1);		//south 3
		boundingBoxes[4] = new Cuboid6(0,     0,  0.5-w, 0.5-w, h, 0.5+w);	//west 4
		boundingBoxes[5] = new Cuboid6(0.5+w, 0,  0.5-w, 1,     h, 0.5+w);	//east 5
	}


	@Override
	public Iterable<Cuboid6> getCollisionBoxes() {
		ArrayList<Cuboid6> t2 = new ArrayList<Cuboid6>();
		t2.add(boundingBoxes[0]);
		for(int i=2; i<6; i++){
			if(isConnectedOnSide(ForgeDirection.getOrientation(i))){
				t2.add(boundingBoxes[i]);
			}
		}
		return t2;
	}
	
	@Override
    public Iterable<IndexedCuboid6> getSubParts() {
        Iterable<Cuboid6> boxList = getCollisionBoxes();
        LinkedList<IndexedCuboid6> partList = new LinkedList<IndexedCuboid6>();
        for (Cuboid6 c : boxList)
            partList.add(new IndexedCuboid6(0, c));
        ((ArrayList<Cuboid6>) boxList).clear();
        
        return partList;
    }
    
    @Override
    public boolean occlusionTest(TMultiPart part) {
    
        return NormalOcclusionTest.apply(this, part);
    }

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[0] });
	}

	boolean isConnectedOnSide(ForgeDirection o) {
		return conn.containsKey(o) && conn.get(o);
	}
	
	public void updateConnections(){
		if(tile() == null)return;
		if(!tile().getWorldObj().isRemote)if(tile().getWorldObj().getBlock(x(), y()-1, z()) == null || !tile().getWorldObj().getBlock(x(), y()-1, z()).isBlockSolid(tile().getWorldObj(), x(), y()-1, z(), ForgeDirection.UP.ordinal())){
			this.tile().dropItems(this.getDrops());
			try{
			this.tile().remPart(this);
			}catch(Exception e){}
			return;
		}
		conn.clear();
		for(int i=2;i<6;i++){
			boolean a = tile().canAddPart(new NormallyOccludedPart(boundingBoxes[i]));
			boolean b = false;
			TileEntity tile = IGL_Utils.getRelative(tile(), ForgeDirection.getOrientation(i));
			if(MicroPartUtil.canConect(this,tile,ForgeDirection.getOrientation(i)))b = true;
			conn.put(ForgeDirection.getOrientation(i), a && b);
		}
	}

	//configIO
	@Override
	public String getType() {
		return MicroRegistry.BasicCable.getUnlocalizedName();
	}

	//energy
	@Override
	public PowerInterface getPower() {
		if(cond == null){
			cond = new PowerInterface(tile()){
				public ForgeDirection[] getConnectableSides(){
					return new ForgeDirection[]{ForgeDirection.DOWN,ForgeDirection.EAST,ForgeDirection.NORTH,ForgeDirection.SOUTH,ForgeDirection.WEST};
				}
				
				public CableType getConnectionType(ForgeDirection side){
					if(side == ForgeDirection.UP)return CableType.NOTHING;
					return CableType.RIBBON_BOTTOM;
				}
			};
			
		}
		return cond;
	}

	@Override
	public void update() {
		super.update();
		getPower().MachineUpdate();
		updateConnections();
	}

	@Override
	public void onNeighborChanged() {
		if(!world().isRemote)IGLNet_Utils.INSTANCE.sendToAll(new MessageMicroPartUpdate(this));
		updateConnections();
	}

	@Override
	public void onPartChanged(TMultiPart part) {
		updateConnections();
	}

	@Override
	public void onAdded() {
		getPower().iterate();
		updateConnections();
	}

	@Override
	public void onRemoved() {
		if(getPower().getNetwork() != null){
			getPower().getNetwork().excludeAndRecalculate(this);
		}
	}

	//multipart

	@Override
	public int getSlotMask() {
		
		return PartMap.BOTTOM.mask;
	}

	@Override
	public Iterable<ItemStack> getDrops() {
		LinkedList<ItemStack> items = new LinkedList<ItemStack>();
		items.add(new ItemStack(MicroRegistry.BasicCable));
		return items;
	}

	@Override
	public ItemStack pickItem(MovingObjectPosition hit) {

		return new ItemStack(MicroRegistry.BasicCable);
	}

	@Override
	public float getStrength(MovingObjectPosition hit, EntityPlayer player) {

		return 1f;
	}

	//render

	private RenderCablePlane render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			if (render == null) render = new RenderCablePlane();
			render.render(this, pos);
		}
		GL11.glPushMatrix();
		GL11.glTranslated(pos.x, pos.y, pos.z);
//		RenderUtils.drawCuboidOutline(boundingBoxes[0]);
//		RenderUtils.drawCuboidOutline(boundingBoxes[2]);
//		RenderUtils.drawCuboidOutline(boundingBoxes[3]);
//		RenderUtils.drawCuboidOutline(boundingBoxes[4]);
//		RenderUtils.drawCuboidOutline(boundingBoxes[5]);
		GL11.glPopMatrix();
	}

	@Override
	public int redstoneConductionMap() {
		return 0xF;
	}

	@Override
	public boolean solid(int arg0) {
		return false;
	}

	@Override
	public void onNeigUpdate() {
		onNeighborChanged();
	}
}


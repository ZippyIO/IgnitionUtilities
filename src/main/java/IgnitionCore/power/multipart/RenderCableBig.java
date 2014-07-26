package IgnitionCore.power.multipart;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import IgnitionCore.misc.IGL_Utils;
import IgnitionCore.misc.RenderUtil;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;


import cpw.mods.fml.common.Optional;

public class RenderCableBig{
	
	private ModelBigCable model;
	private ResourceLocation tex = new ResourceLocation("ignitionutilities:textures/misc/cable/bigcable.png");
	
	public RenderCableBig(){
		model = new ModelBigCable();
	}

	@Optional.Method(modid = "ForgeMultipart")
	public void render(IgnitionCore.power.multipart.MicroCableBig microCableBig, Vector3 pos) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(pos.x, pos.y, pos.z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		boolean[] b = new boolean[6];
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			b[d.ordinal()] = canConnectTo(microCableBig, d);
		}
		int i;
		if((i = RenderUtil.getDir(b)) != -1){
			RenderUtil.bindTexture(new ResourceLocation("ignitionutilities:textures/misc/cable/bigcable_"+i+".png"));
			
		}else RenderUtil.bindTexture(tex);
		model.render(0.0625f,b);
		GL11.glPopMatrix();
	}
	
	private boolean canConnectTo(IgnitionCore.power.multipart.MicroCableBig microCableBig, ForgeDirection o) {
		boolean a = microCableBig.tile().canAddPart(new NormallyOccludedPart(microCableBig.boundingBoxes[o.ordinal()]));
		boolean b = false;
		TileEntity tile = IGL_Utils.getRelative(microCableBig.tile(), o);
		if(MicroPartUtil.canConect(microCableBig,tile,o))b = true;
		if(o == ForgeDirection.DOWN){
			for(TMultiPart t : microCableBig.tile().jPartList())if(t instanceof MicroCablePlane){b = true; a = true;}
		}
		return a && b;
	}


}


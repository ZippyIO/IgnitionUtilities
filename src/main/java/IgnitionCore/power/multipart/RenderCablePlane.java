package IgnitionCore.power.multipart;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import IgnitionCore.misc.IGL_Utils;
import IgnitionCore.misc.RenderUtil;
import IgnitionCore.model.ModelPlaneCable;
import IgnitionCore.model.ModelPlaneCableBase;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;


public class RenderCablePlane{

	private ModelPlaneCable model;
	private ModelPlaneCableBase base;
	
	public RenderCablePlane(){
		model = new ModelPlaneCable();
		base = new ModelPlaneCableBase();
	}
	public void render(IgnitionCore.power.multipart.MicroCablePlane microCablePlane, Vector3 pos) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) pos.x + 0.5F, (float) pos.y + 1.5F, (float) pos.z + 0.5F);
		RenderUtil.bindTexture(new ResourceLocation("ignitionutilities:textures/misc/cable/plane.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		boolean[] a = new boolean[4];
		for(int d = 2; d<6;d++){
			a[d-2] = canConect(microCablePlane, ForgeDirection.getOrientation(d));
		}
		model.render(0.0625F,a);
		int i = RenderUtil.getDir(a);
		if(i == -1)i = 0;
		RenderUtil.bindTexture(new ResourceLocation("ignitionutilities:textures/misc/cable/planecablebase_"+i+".png"));
		base.render(0.0625f);
		GL11.glPopMatrix();
	}
	private boolean canConect(IgnitionCore.power.multipart.MicroCablePlane microCablePlane, ForgeDirection i) {
		boolean a = microCablePlane.tile().canAddPart(new NormallyOccludedPart(microCablePlane.boundingBoxes[i.ordinal()]));
		boolean b = false;
		TileEntity tile = IGL_Utils.getRelative(microCablePlane.tile(), i);
		if(MicroPartUtil.canConect(microCablePlane,tile,i))b = true;
		return a && b;
	}

}

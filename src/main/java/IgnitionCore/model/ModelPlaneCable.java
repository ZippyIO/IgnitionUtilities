package IgnitionCore.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlaneCable extends ModelBase
{
	//fields
	ModelRenderer DF;
	ModelRenderer DB;
	ModelRenderer DR;
	ModelRenderer DL;

	public ModelPlaneCable()
	{
		textureWidth = 20;
		textureHeight = 32;

		DF = new ModelRenderer(this, 0, 7);
		DF.addBox(-2F, 0F, -8F, 4, 3, 6);
		DF.setRotationPoint(0F, 21F, 0F);
		DF.setTextureSize(20, 32);
		DF.mirror = true;
		setRotation(DF, 0F, 0F, 0F);
		DB = new ModelRenderer(this, 0, 16);
		DB.addBox(-2F, 0F, 2F, 4, 3, 6);
		DB.setRotationPoint(0F, 21F, 0F);
		DB.setTextureSize(20, 32);
		DB.mirror = true;
		setRotation(DB, 0F, 0F, 0F);
		DR = new ModelRenderer(this, 0, 25);
		DR.addBox(-8F, 0F, -2F, 6, 3, 4);
		DR.setRotationPoint(0F, 21F, 0F);
		DR.setTextureSize(20, 32);
		DR.mirror = true;
		setRotation(DR, 0F, 0F, 0F);
		DL = new ModelRenderer(this, 0, 0);
		DL.addBox(2F, 0F, -2F, 6, 3, 4);
		DL.setRotationPoint(0F, 21F, 0F);
		DL.setTextureSize(20, 32);
		DL.mirror = true;
		setRotation(DL, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		DF.render(f5);
		DB.render(f5);
		DR.render(f5);
		DL.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

	public void render(float f5, boolean[] a) {
		if(a == null)return;
		if(a[0])DF.render(f5);
		if(a[1])DB.render(f5);
		if(a[2])DL.render(f5);
		if(a[3])DR.render(f5);
	}

	public void renderItem(float f5) {
		DF.render(f5);
		DB.render(f5);
		DR.render(f5);
		DL.render(f5);

	}

}


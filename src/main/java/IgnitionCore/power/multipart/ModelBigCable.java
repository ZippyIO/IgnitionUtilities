package IgnitionCore.power.multipart;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBigCable extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer Down;
    ModelRenderer Up;
    ModelRenderer North;
    ModelRenderer South;
    ModelRenderer Left;
    ModelRenderer Right;
  
  public ModelBigCable()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      base = new ModelRenderer(this, 0, 0);
      base.addBox(-4F, -4F, -4F, 8, 8, 8);
      base.setRotationPoint(0F, 16F, 0F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      Down = new ModelRenderer(this, 0, 20);
      Down.addBox(-4F, 4F, -4F, 8, 4, 8);
      Down.setRotationPoint(0F, 16F, 0F);
      Down.setTextureSize(64, 32);
      Down.mirror = true;
      setRotation(Down, 0F, 0F, 0F);
      Up = new ModelRenderer(this, 0, 20);
      Up.addBox(-4F, -8F, -4F, 8, 4, 8);
      Up.setRotationPoint(0F, 16F, 0F);
      Up.setTextureSize(64, 32);
      Up.mirror = true;
      setRotation(Up, 0F, 0F, 0F);
      North = new ModelRenderer(this, 40, 0);
      North.addBox(-4F, -4F, -8F, 8, 8, 4);
      North.setRotationPoint(0F, 16F, 0F);
      North.setTextureSize(64, 32);
      North.mirror = true;
      setRotation(North, 0F, 0F, 0F);
      South = new ModelRenderer(this, 40, 0);
      South.addBox(-4F, -4F, 4F, 8, 8, 4);
      South.setRotationPoint(0F, 16F, 0F);
      South.setTextureSize(64, 32);
      South.mirror = true;
      setRotation(South, 0F, 0F, 0F);
      Left = new ModelRenderer(this, 40, 16);
      Left.addBox(4F, -4F, -4F, 4, 8, 8);
      Left.setRotationPoint(0F, 16F, 0F);
      Left.setTextureSize(64, 32);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 40, 16);
      Right.addBox(-8F, -4F, -4F, 4, 8, 8);
      Right.setRotationPoint(0F, 16F, 0F);
      Right.setTextureSize(64, 32);
      Right.mirror = true;
      setRotation(Right, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    base.render(f5);
    Down.render(f5);
    Up.render(f5);
    North.render(f5);
    South.render(f5);
    Left.render(f5);
    Right.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

//  @Optional.Method(modid = "ForgeMultipart")
//  public void render(float f5, MicroCableBig mc) {
//	  mc.updateConnections();
//	  Map<ForgeDirection, Boolean> c = mc.conn;
//	  base.render(f5);
//	  if(c.get(ForgeDirection.UP))Down.render(f5);
//	  if(c.get(ForgeDirection.DOWN))Up.render(f5);
//	  if(c.get(ForgeDirection.NORTH))North.render(f5);
//	  if(c.get(ForgeDirection.SOUTH))South.render(f5);
//	  if(c.get(ForgeDirection.WEST))Right.render(f5);
//	  if(c.get(ForgeDirection.EAST))Left.render(f5);
//  }

  public void renderItem(float f5) {
	  base.render(f5);
	  Down.render(f5);
	  Up.render(f5);
	  North.render(f5);
	  South.render(f5);
	  Left.render(f5);
	  Right.render(f5);
  }

  public void render(float f5, boolean[] b) {
	  base.render(f5);
	  if(b == null)return;
	  if(b[1])Down.render(f5);
	  if(b[0])Up.render(f5);
	  if(b[2])North.render(f5);
	  if(b[3])South.render(f5);
	  if(b[4])Right.render(f5);
	  if(b[5])Left.render(f5);
  }

}


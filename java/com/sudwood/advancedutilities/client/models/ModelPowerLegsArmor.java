// Date: 7/18/2015 8:54:47 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package com.sudwood.advancedutilities.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPowerLegsArmor extends ModelBiped
{
  //fields
    ModelRenderer lefttank;
    ModelRenderer leftpart4;
    ModelRenderer leftbackplate;
    ModelRenderer leftknee2;
    ModelRenderer leftpart1;
    ModelRenderer leftkneebrace;
    ModelRenderer leftbend;
    ModelRenderer leftpart3;
    ModelRenderer leftpart2;
    ModelRenderer leftsideplate;
    ModelRenderer leftfrontplat;
    ModelRenderer rightbackplate;
    ModelRenderer righttank;
    ModelRenderer rightpart2;
    ModelRenderer rightknee2;
    ModelRenderer rightpart4;
    ModelRenderer rightpart1;
    ModelRenderer rightbend;
    ModelRenderer rightpart3;
    ModelRenderer rightkneebrace;
    ModelRenderer rightsideplate;
    ModelRenderer rightfrontplat;
  
  public ModelPowerLegsArmor(float f)
  {
	  super(f, 0, 128, 128);
    textureWidth = 128;
    textureHeight = 128;
    
      lefttank = new ModelRenderer(this, 0, 25);
      lefttank.addBox(0F, 0F, 0F, 2, 5, 1);
      lefttank.setRotationPoint(0F, 2F, 2F);
      lefttank.setTextureSize(128, 128);
      lefttank.mirror = true;
      setRotation(lefttank, 0F, 0F, 0F);
      leftpart4 = new ModelRenderer(this, 0, 7);
      leftpart4.addBox(0F, 0F, 0F, 1, 1, 2);
      leftpart4.setRotationPoint(3F, 7F, 1F);
      leftpart4.setTextureSize(128, 128);
      leftpart4.mirror = true;
      setRotation(leftpart4, 0F, 0F, 0F);
      leftbackplate = new ModelRenderer(this, 52, 0);
      leftbackplate.addBox(0F, 0F, 0F, 4, 12, 0);
      leftbackplate.setRotationPoint(0F, 1F, 2F);
      leftbackplate.setTextureSize(128, 128);
      leftbackplate.mirror = true;
      setRotation(leftbackplate, 0F, 0F, 0F);
      leftknee2 = new ModelRenderer(this, 18, 0);
      leftknee2.addBox(0F, 0F, 0F, 4, 1, 1);
      leftknee2.setRotationPoint(-1F, 7F, 2F);
      leftknee2.setTextureSize(128, 128);
      leftknee2.mirror = true;
      setRotation(leftknee2, 0F, 0F, 0F);
      leftpart1 = new ModelRenderer(this, 0, 0);
      leftpart1.addBox(0F, 0F, 0F, 1, 5, 1);
      leftpart1.setRotationPoint(3F, 1F, 0F);
      leftpart1.setTextureSize(128, 128);
      leftpart1.mirror = true;
      setRotation(leftpart1, 0F, 0F, 0F);
      leftkneebrace = new ModelRenderer(this, 18, 0);
      leftkneebrace.addBox(0F, 0F, 0F, 4, 1, 1);
      leftkneebrace.setRotationPoint(-1F, 7F, -3F);
      leftkneebrace.setTextureSize(128, 128);
      leftkneebrace.mirror = true;
      setRotation(leftkneebrace, 0F, 0F, 0F);
      leftbend = new ModelRenderer(this, 5, 0);
      leftbend.addBox(0F, 0F, 0F, 1, 2, 2);
      leftbend.setRotationPoint(3F, 6F, -1F);
      leftbend.setTextureSize(128, 128);
      leftbend.mirror = true;
      setRotation(leftbend, 0F, 0F, 0F);
      leftpart3 = new ModelRenderer(this, 0, 7);
      leftpart3.addBox(0F, 0F, 0F, 1, 1, 2);
      leftpart3.setRotationPoint(3F, 7F, -3F);
      leftpart3.setTextureSize(128, 128);
      leftpart3.mirror = true;
      setRotation(leftpart3, 0F, 0F, 0F);
      leftpart2 = new ModelRenderer(this, 12, 0);
      leftpart2.addBox(0F, 0F, 0F, 1, 2, 1);
      leftpart2.setRotationPoint(3F, 8F, 0F);
      leftpart2.setTextureSize(128, 128);
      leftpart2.mirror = true;
      setRotation(leftpart2, 0F, 0F, 0F);
      leftsideplate = new ModelRenderer(this, 43, 0);
      leftsideplate.addBox(0F, 0F, 0F, 0, 12, 4);
      leftsideplate.setRotationPoint(3F, 1F, -2F);
      leftsideplate.setTextureSize(128, 128);
      leftsideplate.mirror = true;
      setRotation(leftsideplate, 0F, 0F, 0F);
      leftfrontplat = new ModelRenderer(this, 33, 0);
      leftfrontplat.addBox(0F, 0F, 0F, 4, 12, 0);
      leftfrontplat.setRotationPoint(-1F, 1F, -2F);
      leftfrontplat.setTextureSize(128, 128);
      leftfrontplat.mirror = true;
      setRotation(leftfrontplat, 0F, 0F, 0F);
      rightbackplate = new ModelRenderer(this, 52, 0);
      rightbackplate.addBox(0F, 0F, 0F, 4, 12, 0);
      rightbackplate.setRotationPoint(-3F, 1F, 2F);
      rightbackplate.setTextureSize(128, 128);
      rightbackplate.mirror = true;
      setRotation(rightbackplate, 0F, 0F, 0F);
      righttank = new ModelRenderer(this, 0, 17);
      righttank.addBox(0F, 0F, 0F, 2, 5, 1);
      righttank.setRotationPoint(-2F, 2F, 2F);
      righttank.setTextureSize(128, 128);
      righttank.mirror = true;
      setRotation(righttank, 0F, 0F, 0F);
      rightpart2 = new ModelRenderer(this, 12, 0);
      rightpart2.addBox(0F, 0F, 0F, 1, 2, 1);
      rightpart2.setRotationPoint(-4F, 8F, 0F);
      rightpart2.setTextureSize(128, 128);
      rightpart2.mirror = true;
      setRotation(rightpart2, 0F, 0F, 0F);
      rightknee2 = new ModelRenderer(this, 18, 0);
      rightknee2.addBox(0F, 0F, 0F, 4, 1, 1);
      rightknee2.setRotationPoint(-3F, 7F, 2F);
      rightknee2.setTextureSize(128, 128);
      rightknee2.mirror = true;
      setRotation(rightknee2, 0F, 0F, 0F);
      rightpart4 = new ModelRenderer(this, 0, 7);
      rightpart4.addBox(0F, 0F, 0F, 1, 1, 2);
      rightpart4.setRotationPoint(-4F, 7F, 1F);
      rightpart4.setTextureSize(128, 128);
      rightpart4.mirror = true;
      setRotation(rightpart4, 0F, 0F, 0F);
      rightpart1 = new ModelRenderer(this, 0, 0);
      rightpart1.addBox(0F, 0F, 0F, 1, 5, 1);
      rightpart1.setRotationPoint(-4F, 1F, 0F);
      rightpart1.setTextureSize(128, 128);
      rightpart1.mirror = true;
      setRotation(rightpart1, 0F, 0F, 0F);
      rightbend = new ModelRenderer(this, 5, 0);
      rightbend.addBox(0F, 0F, 0F, 1, 2, 2);
      rightbend.setRotationPoint(-4F, 6F, -1F);
      rightbend.setTextureSize(128, 128);
      rightbend.mirror = true;
      setRotation(rightbend, 0F, 0F, 0F);
      rightpart3 = new ModelRenderer(this, 0, 7);
      rightpart3.addBox(0F, 0F, 0F, 1, 1, 2);
      rightpart3.setRotationPoint(-4F, 7F, -3F);
      rightpart3.setTextureSize(128, 128);
      rightpart3.mirror = true;
      setRotation(rightpart3, 0F, 0F, 0F);
      rightkneebrace = new ModelRenderer(this, 18, 0);
      rightkneebrace.addBox(0F, 0F, 0F, 4, 1, 1);
      rightkneebrace.setRotationPoint(-3F, 7F, -3F);
      rightkneebrace.setTextureSize(128, 128);
      rightkneebrace.mirror = true;
      setRotation(rightkneebrace, 0F, 0F, 0F);
      rightsideplate = new ModelRenderer(this, 43, 0);
      rightsideplate.addBox(0F, 0F, 0F, 0, 12, 4);
      rightsideplate.setRotationPoint(-3F, 1F, -2F);
      rightsideplate.setTextureSize(128, 128);
      rightsideplate.mirror = true;
      setRotation(rightsideplate, 0F, 0F, 0F);
      rightfrontplat = new ModelRenderer(this, 33, 0);
      rightfrontplat.addBox(0F, 0F, 0F, 4, 12, 0);
      rightfrontplat.setRotationPoint(-3F, 1F, -2F);
      rightfrontplat.setTextureSize(128, 128);
      rightfrontplat.mirror = true;
      setRotation(rightfrontplat, 0F, 0F, 0F);
      
      this.bipedLeftLeg.addChild(lefttank);
      this.bipedLeftLeg.addChild(leftpart4);
      this.bipedLeftLeg.addChild(leftbackplate);
      this.bipedLeftLeg.addChild(leftknee2);
      this.bipedLeftLeg.addChild(leftpart1);
      this.bipedLeftLeg.addChild(leftkneebrace);
      this.bipedLeftLeg.addChild(leftbend);
      this.bipedLeftLeg.addChild(leftpart3);
      this.bipedLeftLeg.addChild(leftsideplate);
      this.bipedLeftLeg.addChild(leftfrontplat);
      
      this.bipedRightLeg.addChild(righttank);
      this.bipedRightLeg.addChild(rightpart4);
      this.bipedRightLeg.addChild(rightbackplate);
      this.bipedRightLeg.addChild(rightknee2);
      this.bipedRightLeg.addChild(rightpart1);
      this.bipedRightLeg.addChild(rightkneebrace);
      this.bipedRightLeg.addChild(rightbend);
      this.bipedRightLeg.addChild(rightpart3);
      this.bipedRightLeg.addChild(rightsideplate);
      this.bipedRightLeg.addChild(rightfrontplat);

      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    /*lefttank.render(f5);
    leftpart4.render(f5);
    leftbackplate.render(f5);
    leftknee2.render(f5);
    leftpart1.render(f5);
    leftkneebrace.render(f5);
    leftbend.render(f5);
    leftpart3.render(f5);
    leftpart2.render(f5);
    leftsideplate.render(f5);
    leftfrontplat.render(f5);
    rightbackplate.render(f5);
    righttank.render(f5);
    rightpart2.render(f5);
    rightknee2.render(f5);
    rightpart4.render(f5);
    rightpart1.render(f5);
    rightbend.render(f5);
    rightpart3.render(f5);
    rightkneebrace.render(f5);
    rightsideplate.render(f5);
    rightfrontplat.render(f5);*/
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

}

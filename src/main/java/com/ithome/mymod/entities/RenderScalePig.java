package com.ithome.mymod.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScalePig extends RenderLiving {

    private static final ResourceLocation textures = new ResourceLocation("textures/entity/pig/pig.png");

    private float scale = 1.0f;

    public RenderScalePig(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    protected void preRenderCallback(EntityLivingBase entityLivingBase, float tickTime)
    {
        GlStateManager.scale(this.scale, this.scale, this.scale);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return textures;
    }
}

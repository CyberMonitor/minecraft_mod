package com.ithome.mymod.entities;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.World;

public class EntityScalePig extends EntityPig {

    public static final String name = "MyPig";

    public EntityScalePig(World worldIn) {
        super(worldIn);
    }

    public void scale(float scale) {
        this.setSize(width * scale, height * scale);
    }
}

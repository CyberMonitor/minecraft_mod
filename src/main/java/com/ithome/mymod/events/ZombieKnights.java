package com.ithome.mymod.events;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ZombieKnights {

    @SubscribeEvent
    public void giveArmor(EntityJoinWorldEvent event) {
        if(event.entity instanceof EntityZombie) {
            event.entity.setCurrentItemOrArmor(0, new ItemStack(Items.diamond_sword));
            event.entity.setCurrentItemOrArmor(1, new ItemStack(Items.diamond_chestplate));
            event.entity.setCurrentItemOrArmor(2, new ItemStack(Items.diamond_leggings));
            event.entity.setCurrentItemOrArmor(3, new ItemStack(Items.diamond_boots));
            event.entity.setCurrentItemOrArmor(4, new ItemStack(Items.diamond_helmet));
        }
    }
}

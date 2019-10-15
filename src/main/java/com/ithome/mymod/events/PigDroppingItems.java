package com.ithome.mymod.events;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class PigDroppingItems {

    @SubscribeEvent
    public void dropDiamonds(LivingDeathEvent event) {
        // 判斷這個死亡的實體是不是豬 (EntityPig)
        if(event.entity instanceof EntityPig) {

            // 建立隨機種子
            Random random = new Random();
            // 如果這個是server端呼叫才執行
            if(!event.entity.worldObj.isRemote) {
                // 讓豬掉下的物品變成鑽石，並且掉下的數量從0 ~ 4顆
                event.entity.dropItem(Items.diamond, random.nextInt(5));
            }
        }
    }
}

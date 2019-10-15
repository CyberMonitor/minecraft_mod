package com.ithome.mymod.events;

import com.ithome.mymod.MyMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class SuperJump {
    @SubscribeEvent
    public void equipParachute(PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(player.isSneaking()) {
            player.motionY = -0.05;
        }
    }

    @SubscribeEvent
    public void jumpHigher(LivingJumpEvent event) {
        if(event.entity instanceof EntityPlayer) {
            event.entity.motionY *= MyMod.jumpY;
        }
    }

    @SubscribeEvent
    public void avoidFallDamage(LivingFallEvent event) {
        // 是玩家才進行處理
        if(event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            // 當玩家為潛行狀態時，將此事件取消 (即沒有"掉落"這個狀態，就不會受傷)
            if(player.isSneaking()) {
                event.setCanceled(true);
            }
        }
    }
}

package com.ithome.mymod.events;

import com.ithome.mymod.Utils;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakEvent {

    // 透過註釋來告訴Forge這個方法是一個事件處理器(EventHandler)可以處理的事件
    // BreakEvent是一個事件(定義在BlockEvent下)，這裡我們要處理打破方塊這個事件發生時的動作
    @SubscribeEvent
    public void sendMessage(BreakEvent event) {
        // 取得打破方塊的玩家資訊
        event.getPlayer()
                // 加入一個訊息到聊天室窗中
                .addChatComponentMessage(
                        // 定義訊息內容為：彩色文字
                        new ChatComponentText(Utils.rainbowString("Congratulations! You broke a block!"))
                );
    }

    @SubscribeEvent
    public void explodeOre(BreakEvent event) {
        // 透過事件取得被破壞的方塊，若方塊不為礦石方塊(BlockOre)，則不做任何事情(return)
        // 這裡需要import net.minecraft.block.BlockOre
        if (!(event.state.getBlock() instanceof BlockOre)) return;

        BlockPos blockPos = event.pos;
        System.out.println(blockPos.offset(EnumFacing.getHorizontal(1)));

        // 爆炸的來源需要實體，這裡指定玩家 (需要import net.minecraft.entity.player.EntityPlayer)
        EntityPlayer player = event.getPlayer();
        // 爆破的威力 (2代表兩個方塊長的爆炸範圍)
        float power = 2;
        // 爆炸是否要損毀方塊
        boolean destroyBlocks = true;
        // 建立爆破
        event.world.createExplosion(
                // 爆炸來源為玩家
                player,
                // 藉由打破方塊事件的位置來建立爆破
                event.pos.getX(),
                event.pos.getY(),
                event.pos.getZ(),
                power,
                destroyBlocks
        );
    }
}
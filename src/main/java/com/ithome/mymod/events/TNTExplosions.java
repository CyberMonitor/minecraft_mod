package com.ithome.mymod.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class TNTExplosions {

    // 威力使用20格
    private float power = 20;

    @SubscribeEvent
    public void explodeHead(EntityJoinWorldEvent event) {
        // 只處理加入到世界的實體是TNT : 這裡即是指TNT方塊被啟動後閃爍的TNT"實體"
        if (event.entity instanceof EntityTNTPrimed) {
            Entity entity = event.entity;
            // 建立我們想要的爆炸威力
            event.entity.worldObj.createExplosion(
                    entity,
                    entity.posX,
                    entity.posY,
                    entity.posZ,
                    power,
                    true
            );
        }
    }

    @SubscribeEvent
    public void explodeTunnel(ExplosionEvent.Detonate event) {

        if (event.world.isRemote) {
            return;
        }

        Vec3 eventPos = event.explosion.getPosition();
        event.getAffectedBlocks().clear();

        int height = 6;
        int depth = 20;
        toCave(event.getAffectedBlocks(), eventPos, height, depth);
    }

    private void toCave(List<BlockPos> affectBlocks, Vec3 originPos, int height, int depth) {
        for (int x = -height; x <= height; x++) {
            for (int y = -height; (y <= x && x <= 0) || (Math.abs(y) >= x && x > 0); y++) {
                int trueY = height - Math.abs(y);

                for (int z = 0; z <= depth; z++) {
                    BlockPos b = new BlockPos(originPos.xCoord + x, originPos.yCoord + trueY, originPos.zCoord + z);
                    affectBlocks.add(b);
                }
            }
        }
    }
}

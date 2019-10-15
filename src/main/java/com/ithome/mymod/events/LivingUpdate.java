package com.ithome.mymod.events;

import com.ithome.mymod.entities.HalloweenCreeper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingUpdate {

    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.entity instanceof HalloweenCreeper) {
            BlockPos pos = event.entity.getPosition();
            addLight(event.entity.worldObj, pos.getX(), pos.getY(), pos.getZ(), 10);
        }
    }

    private void addLight(World world, int posX, int posY, int posZ, int lightLevel) {
        world.setLightFor(EnumSkyBlock.BLOCK, new BlockPos(posX, posY, posZ), lightLevel);
        world.markBlockRangeForRenderUpdate( posX, posY, posZ, 12, 12, 12);
        world.updateBlockTick(new BlockPos( posX,  posY, posZ),
                world.getBlockState(new BlockPos( posX,  posY, posZ)).getBlock(), 1, 0);
        world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos( posX, posY + 1, posZ));
        world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos( posX, posY - 1, posZ));
        world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(posX + 1, posY, posZ));
        world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(posX - 1, posY, posZ));
        world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(posX, posY, posZ + 1));
        world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(posX, posY, posZ - 1));
    }
}

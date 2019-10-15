package com.ithome.mymod.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class EntityAIHalloween extends EntityAIBase {

    private final EntityMob entityMob;
    // 判斷碰撞的時間，避免太頻繁執行
    public static long timer;

    public EntityAIHalloween(EntityMob entityMob) {
        this.entityMob = entityMob;
        // 7可以與大部分的AI工作同時發生
        setMutexBits(7);
    }

    @Override
    public boolean shouldExecute() {
        if(entityMob.getAttackTarget() != null) {
            EntityLivingBase target = entityMob.getAttackTarget();
            float distance = entityMob.getDistanceToEntity(target);
            if(distance < 2.0F) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void startExecuting() {
        EntityPlayer player = (EntityPlayer)entityMob.getAttackTarget();
        // 往碰撞的玩家送出訊息
        player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "Trick or treat!"));
        // 設定執行時間
        timer = System.currentTimeMillis();
    }

    @Override
    public boolean continueExecuting() {
        // 若當前時間與執行時間相差在2秒內，就繼續這個AI工作來避免下一輪的觸發 (避免太頻繁)
        return System.currentTimeMillis() - timer < 2 * 1000;
    }
}

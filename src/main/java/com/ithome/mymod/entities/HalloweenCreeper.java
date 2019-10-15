package com.ithome.mymod.entities;

import com.ithome.mymod.ai.EntityAIHalloween;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HalloweenCreeper extends EntityCreeper {
    public static String name = "HalloweenCreeper";

    public HalloweenCreeper(World worldIn) {
        super(worldIn);

        setupUI();
    }

    private void setupUI() {
        this.tasks.taskEntries.clear();
        this.targetTasks.taskEntries.clear();

        // 送訊息給玩家的工作
        this.tasks.addTask(0, new EntityAIHalloween(this));
        // 閒晃
        this.tasks.addTask(1, new EntityAIWander(this, 0.8D));
        // 尋找玩家與靠近
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, false));
        // Idle狀態
        this.tasks.addTask(4, new EntityAILookIdle(this));
        // 設定目標的工作
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
}

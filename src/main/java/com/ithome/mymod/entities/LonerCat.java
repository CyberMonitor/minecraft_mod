package com.ithome.mymod.entities;

import com.ithome.mymod.ai.EntityAILoner;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicBoolean;

public class LonerCat extends EntityOcelot {

    // 孤僻貓的類別ID
    public static final String name = "LonerCat";
    // 是否為第一次與其它實體碰撞
    public static AtomicBoolean firstHit = new AtomicBoolean(true);
    // 與這個實體碰撞的玩家
    public static EntityPlayer thePlayer;

    public LonerCat(World worldIn) {
        super(worldIn);
        // 設置AI
        setupUI();
    }

    private void setupUI() {
        this.tasks.taskEntries.clear();
        this.targetTasks.taskEntries.clear();
        // 自定義的AI工作
        tasks.addTask(0, new EntityAILoner(this));
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        // 是第一次被碰撞 (第一個參數先判斷為true後，就透過第二個參數將這個firstHit更改為false)
        if(firstHit.compareAndSet(true, false)) {
            // 將AI工作內的判斷變數改為true
            EntityAILoner.isHit = true;
            // 設定玩家物件
            thePlayer = player;
        }
    }
}

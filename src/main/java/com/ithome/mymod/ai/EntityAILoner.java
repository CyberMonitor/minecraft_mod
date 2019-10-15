package com.ithome.mymod.ai;

import com.ithome.mymod.entities.LonerCat;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityAILoner extends EntityAIBase {

    private static List<String> messages = new ArrayList<String>();
    static {
        messages.add("Don't touch me!");
        messages.add("Mew~~~~~~~");
        messages.add("Are you alone?");
        messages.add("Do you wanna be a cat?");
        messages.add("What does the fox say?");
    }
    // 定義實體的物件
    private final EntityAnimal animal;
    // 用來判斷是否需要執行這個AI工作
    public static boolean isHit = false;
    // 判斷碰撞的時間，避免太頻繁執行
    public static long timer;

    public EntityAILoner(EntityAnimal entity) {
        this.animal = entity;
        // 7可以與大部分的AI工作同時發生
        setMutexBits(7);
    }


    @Override
    public boolean shouldExecute() {
        // 如果碰撞了，開始執行AI工作
        if(isHit) {
            // 將該變數設定為false等待下次碰撞發生
            isHit = false;
            return true;
        } else {
            // 其餘狀況都不執行AI工作
            return false;
        }
    }

    @Override
    public void startExecuting() {
        // 往碰撞的玩家送出隨機的訊息
        LonerCat.thePlayer.addChatComponentMessage(new ChatComponentText(
                EnumChatFormatting.RED + messages.get(new Random().nextInt(messages.size()))
        ));
        // 設定執行時間
        timer = System.currentTimeMillis();
    }

    @Override
    public boolean continueExecuting() {
        // 若當前時間與執行時間相差在2秒內，就繼續這個AI工作來避免下一輪的觸發 (避免太頻繁)
        if(System.currentTimeMillis() - timer < 2 * 1000) {
            return true;
        } else {
            // 此次AI工作完成，讓LonerCat實體可以開始下一次的碰撞判斷事件了
            LonerCat.firstHit.compareAndSet(false, true);
            return false;
        }
    }
}

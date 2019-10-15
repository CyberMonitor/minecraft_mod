package com.ithome.mymod.proxy;

import com.ithome.mymod.MyMod;
import com.ithome.mymod.events.GuiEvent;
import com.ithome.mymod.events.SuperJump;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.Map;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Override
    public void init(FMLInitializationEvent event) {
        for(Map.Entry<Object, Boolean> entry : MyMod.eventTriggerMap.entrySet()) {
            // 若該事件的設定是false，就取消註冊
            if(!entry.getValue()) {
                MinecraftForge.EVENT_BUS.unregister(entry.getKey());
                if(entry.getKey() instanceof SuperJump) {
                    FMLCommonHandler.instance().bus().unregister(entry.getKey());
                }
                // 反之，就註冊
            } else {
                MinecraftForge.EVENT_BUS.register(entry.getKey());
                if(entry.getKey() instanceof SuperJump) {
                    FMLCommonHandler.instance().bus().register(entry.getKey());
                }
            }
        }

        // 註冊GUI設定檔事件
        MinecraftForge.EVENT_BUS.register(new GuiEvent());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        // 0 means the OverWorld (主世界)
        // -1 means the Nether (地獄)
        // 1 means the Ent (終界)
        WorldServer world = event.getServer().worldServerForDimension(0);

        // 更改時間為半夜0時 (18000 = 第15分鐘 * 60秒 * 20刻)
        world.setWorldTime(18000);

        // 將gamerule中的日夜交替動作取消
        world.getGameRules().setOrCreateGameRule("doDaylightCycle", "false");
    }
}

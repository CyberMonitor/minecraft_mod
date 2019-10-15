package com.ithome.mymod.configs;

import com.ithome.mymod.MyMod;
import com.ithome.mymod.events.SuperJump;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.Map;

public class CustomConfig {

    public static Configuration configuration;

    // 初始化設定檔
    public static void init(FMLPreInitializationEvent event) {

        String configDir = event.getModConfigurationDirectory().toString();
        if(configuration == null) {
            File path = new File(configDir + "/" + Constants.MOD_ID + ".cfg");

            configuration = new Configuration(path);

            loadConfiguration();
        }

    }

    // 讀取設定檔
    private static void loadConfiguration() {
        MyMod.jumpY = configuration.getInt("Jump motionY", Configuration.CATEGORY_GENERAL, 5, 1, 10, "How height you can jump");

        // 透過eventTriggerMap的key name來寫入到設定檔，並且將設定檔內的value寫到透過eventTriggerMap內
        for(Map.Entry<Object, Boolean> entry : MyMod.eventTriggerMap.entrySet()) {
            Boolean b = configuration.get(Configuration.CATEGORY_GENERAL,"Enable " + entry.getKey().getClass().getSimpleName(), true).getBoolean();
            entry.setValue(b);
        }

        // 當設定檔有變更，才做儲存
        if(configuration.hasChanged()) {
            configuration.save();
        }
    }

    // 取得Configuration物件
    public static Configuration getConfiguration() {
        return configuration;
    }

    @SubscribeEvent
    public void onConfigurationChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(event.modID.equalsIgnoreCase(Constants.MOD_ID)) {
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void postConfigChange(ConfigChangedEvent.PostConfigChangedEvent event) {

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
    }
}

package com.ithome.mymod.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public interface IProxy {
    /**
     * 準備Minecraft的所有物體。舉凡讀取設定檔、建立方塊、物品，註冊物體到Minecraft等。
     *
     * @param event 事件
     */
    void preInit(FMLPreInitializationEvent event);

    /**
     * 模組設置。例如註冊事件處理、註冊資源、建立配方(合成、燒煉與釀造)。
     *
     * @param event 事件
     */
    void init(FMLInitializationEvent event);

    /**
     * 與其他模組互動。如果有任何其他需要處理的的功能，或是要建立與其他模組的相依關係。
     *
     * @param event 事件
     */
    void postInit(FMLPostInitializationEvent event);

    /**
     * 註冊伺服器指令的地方，這個事件只會被Server呼叫。
     *
     * @param event 事件
     */
    void serverStarting(FMLServerStartingEvent event);
}

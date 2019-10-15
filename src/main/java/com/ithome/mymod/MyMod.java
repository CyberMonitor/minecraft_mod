package com.ithome.mymod;

import com.ithome.mymod.blocks.EnderBlock;
import com.ithome.mymod.commands.Colosseum;
import com.ithome.mymod.configs.Constants;
import com.ithome.mymod.configs.CustomConfig;
import com.ithome.mymod.entities.EntityScalePig;
import com.ithome.mymod.entities.HalloweenCreeper;
import com.ithome.mymod.entities.LonerCat;
import com.ithome.mymod.events.*;
import com.ithome.mymod.items.PillItem;
import com.ithome.mymod.proxy.IProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, guiFactory = Constants.GUI_FACTORY_CLASS)
public class MyMod {

    // 自定義的方塊
    public static Block enderBlock;

    // 自定義的物品
    public static Item pillItem;

    // 自定義屬性
    public static int jumpY;
    public static Map<Object, Boolean> eventTriggerMap = new HashMap<Object, Boolean>();
    static {
        MyMod.eventTriggerMap.put(new BlockBreakEvent(), true);
        MyMod.eventTriggerMap.put(new TNTExplosions(), true);
        MyMod.eventTriggerMap.put(new PigDroppingItems(), true);
        MyMod.eventTriggerMap.put(new ZombieKnights(), true);
        MyMod.eventTriggerMap.put(new SuperJump(), true);
        MyMod.eventTriggerMap.put(new PigDoll(), true);
        MyMod.eventTriggerMap.put(new Colosseum(), true);
        MyMod.eventTriggerMap.put(new LivingUpdate(), true);
    }

    @SidedProxy(serverSide = Constants.SERVER_PROXY, clientSide = Constants.CLIENT_PROXY)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        int availableId = 1;
        while (EntityList.getClassFromID(availableId) != null) {
            availableId++;
        }
        EntityRegistry.registerGlobalEntityID(EntityScalePig.class, EntityScalePig.name, availableId);

        // 註冊LonerCat類別，找一個可以用的ID給它
        while (EntityList.getClassFromID(availableId) != null) {
            availableId++;
        }
        EntityRegistry.registerGlobalEntityID(LonerCat.class, LonerCat.name, availableId);

        // 註冊HalloweenCreeper
        while (EntityList.getClassFromID(availableId) != null) {
            availableId++;
        }
        EntityRegistry.registerGlobalEntityID(HalloweenCreeper.class, HalloweenCreeper.name, availableId);

        enderBlock = new EnderBlock();
        GameRegistry.registerBlock(enderBlock, "enderBlock");
        pillItem = new PillItem();
        GameRegistry.registerItem(pillItem, "pillItem");

        // 註冊設定檔
        CustomConfig.init(event);
        FMLCommonHandler.instance().bus().register(new CustomConfig());

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // 從方塊轉換成物品的方法
        Item item = Item.getItemFromBlock(enderBlock);
        // 定義方塊資源目錄
        // 第一個參數是"{模組ID}:方塊註冊名稱"
        // 第二個參數請使用"inventory"，表示從資源庫內尋找
        ModelResourceLocation location = new ModelResourceLocation(
                "myfancymods:enderBlock",
                "inventory"
        );
        // 註冊這個方塊物品的資源到Forge內
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, location);

        // 註冊藥丸物品的資源到Forge內
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(pillItem, 0, new ModelResourceLocation(
                        "myfancymods:pillItem",
                        "inventory"
                ));

        GameRegistry.addShapedRecipe(
                new ItemStack(enderBlock),
                "X X",
                " X ",
                "X X",
                'X',
                Blocks.dirt);

        GameRegistry.addShapelessRecipe(
                new ItemStack(pillItem, 9),
                enderBlock
        );

        GameRegistry.addSmelting(
                Items.sugar,
                new ItemStack(pillItem, 1),
                1.0f
        );

        pillItem.setPotionEffect(PotionHelper.blazePowderEffect);

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
    }
}

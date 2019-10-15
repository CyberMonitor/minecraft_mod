package com.ithome.mymod.configs;

import com.ithome.mymod.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class CustomGuiConfig extends GuiConfig {
    public CustomGuiConfig(GuiScreen parentScreen) {
        super(parentScreen,
                new ConfigElement(CustomConfig.getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Constants.MOD_ID,
                false,
                false,
                Utils.rainbowString("This is My Mod Configuration"));

        this.initGui();
        titleLine2 = CustomConfig.getConfiguration().getConfigFile().getAbsolutePath();
    }
}

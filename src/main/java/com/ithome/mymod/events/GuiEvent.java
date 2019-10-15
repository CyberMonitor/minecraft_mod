package com.ithome.mymod.events;

import com.ithome.mymod.configs.CustomGuiConfig;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEvent {

    /* this event no need anymore since Forge 1.8.9 will handle the GuiIngameModOptions for us
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onGuiOpenEvent(GuiOpenEvent event) {
        if(event.gui instanceof GuiIngameModOptions) {
            event.gui = new CustomGuiConfig(null);
        }
    }
    */
}

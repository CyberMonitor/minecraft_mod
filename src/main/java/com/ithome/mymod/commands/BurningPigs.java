package com.ithome.mymod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class BurningPigs extends CommandBase {

    // 定義所有允許的指令名稱
    private List<String> aliases = new ArrayList<String>();
    // 產生的燃燒豬數量
    private int numberOfPigs = 0;

    public BurningPigs() {
        aliases.add("burningpigs");
        aliases.add("bp");
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/burningpigs <number of pigs>";
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // 主要邏輯
        if(args == null || args.length != 1) {
            sendErrorMessage(sender, "only accept one argument!");
            return;
        }
        try {
            numberOfPigs = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sendErrorMessage(sender, "The argument \"" + args[0] + "\" is not a valid number!");
            return;
        }

        // 執行指令的是玩家，這裡直接轉型
        EntityPlayer player = (EntityPlayer) sender;

        for(int i = 0 ; i < numberOfPigs ; i++) {
            EntityPig pig = new EntityPig(player.worldObj);
            pig.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
            // 讓豬產生燃燒效果
            pig.setFire(10000);
            player.worldObj.spawnEntityInWorld(pig);
        }
    }

    private void sendErrorMessage(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(
                EnumChatFormatting.DARK_RED + message
        ));
    }
}

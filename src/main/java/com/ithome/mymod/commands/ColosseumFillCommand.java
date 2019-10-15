package com.ithome.mymod.commands;

import com.ithome.mymod.commands.Colosseum;
import net.minecraft.block.BlockAir;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class ColosseumFillCommand extends CommandBase {

    private List aliases = new ArrayList();

    public ColosseumFillCommand() {
        aliases.add("colosseum");
        // 純粹致敬神鬼戰士
        aliases.add("gladiator");
    }

    @Override
    public String getCommandName() {
        return "Fill Colosseum";
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "colosseum";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        // 預設的競技場需要大小
        int widthHalf = 17;
        int lengthHalf = 22;
        int height = 20;

        EntityPlayer player = (EntityPlayer) sender;
        BlockPos pos = player.getPosition();

        for (int x = pos.getX() - widthHalf; x <= pos.getX() + widthHalf; x++) {
            for (int y = pos.getY(); y <= pos.getY() + height; y++) {
                for (int z = pos.getZ() - lengthHalf; z <= pos.getZ() + lengthHalf; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    if (!blockPos.equals(Colosseum.centerPos) &&
                            !(player.worldObj.getBlockState(blockPos).getBlock() instanceof BlockAir)) {
                        player.addChatComponentMessage(new ChatComponentText(
                                EnumChatFormatting.RED +
                                        "The Colosseum could not build in current area because the block : " + blockPos.toString()
                        ));
                        return;
                    }
                }
            }
        }

        System.out.println("centerPos: " + Colosseum.centerPos.toString());

        Colosseum.basicBlocks(player, Colosseum.centerPos);
        Colosseum.wallBlocks(player, Colosseum.centerPos, 2);
        Colosseum.wallBlocks(player, Colosseum.centerPos, 7);
        Colosseum.wallBlocks(player, Colosseum.centerPos, 12);
        Colosseum.innerSeatBlocks(player, Colosseum.centerPos);
    }
}

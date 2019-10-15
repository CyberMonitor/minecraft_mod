package com.ithome.mymod.commands;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Colosseum {

    public static BlockPos centerPos = BlockPos.ORIGIN;

    @SubscribeEvent
    public void chooseCenter(PlayerInteractEvent event) {
        if (event.entityPlayer.getHeldItem() == null ||
                event.entityPlayer.getHeldItem().getItem() != Items.wooden_axe ||
                !event.entityPlayer.capabilities.isCreativeMode) {
            return;
        }

        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            centerPos = event.pos;

            event.entityPlayer.addChatComponentMessage(new ChatComponentText(
                    EnumChatFormatting.GREEN +
                            "Colosseum center position set to " + event.pos.toString()
            ));

            event.setCanceled(true);
        }

    }

    static void basicBlocks(EntityPlayer player, BlockPos centerPos) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        // base part
        for (int height = 0; height <= 2; height++) {
            list.addAll(cardinalBasicEdge(centerPos, height, 0));

            list.addAll(quarterWallEdge(centerPos, true, true, height));
            list.addAll(quarterWallEdge(centerPos, true, false, height));
            list.addAll(quarterWallEdge(centerPos, false, true, height));
            list.addAll(quarterWallEdge(centerPos, false, false, height));
        }
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH));
        }
        list.clear();
    }

    static void wallBlocks(EntityPlayer player, BlockPos centerPos, int layer) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        // fundamental
        list.addAll(cardinalBasicEdge(centerPos, layer, 0));
        list.addAll(cardinalBasicEdge(centerPos, layer, 1));

        list.addAll(wallWindowInnerBottom(centerPos, true, true, layer));
        list.addAll(wallWindowInnerBottom(centerPos, true, false, layer));
        list.addAll(wallWindowInnerBottom(centerPos, false, true, layer));
        list.addAll(wallWindowInnerBottom(centerPos, false, false, layer));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH));
        }
        list.clear();

        // pillar part
        list.addAll(pillarEdge(centerPos, true, true, 1 + layer));
        list.addAll(pillarEdge(centerPos, true, false, 1 + layer));
        list.addAll(pillarEdge(centerPos, false, true, 1 + layer));
        list.addAll(pillarEdge(centerPos, false, false, 1 + layer));
        list.addAll(pillarEdge(centerPos, true, true, 4 + layer));
        list.addAll(pillarEdge(centerPos, true, false, 4 + layer));
        list.addAll(pillarEdge(centerPos, false, true, 4 + layer));
        list.addAll(pillarEdge(centerPos, false, false, 4 + layer));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("quartz_block")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.CHISELED));
        }
        list.clear();

        list.addAll(pillarEdge(centerPos, true, true, 2 + layer));
        list.addAll(pillarEdge(centerPos, true, false, 2 + layer));
        list.addAll(pillarEdge(centerPos, false, true, 2 + layer));
        list.addAll(pillarEdge(centerPos, false, false, 2 + layer));
        list.addAll(pillarEdge(centerPos, true, true, 3 + layer));
        list.addAll(pillarEdge(centerPos, true, false, 3 + layer));
        list.addAll(pillarEdge(centerPos, false, true, 3 + layer));
        list.addAll(pillarEdge(centerPos, false, false, 3 + layer));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("quartz_block")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Y));
        }
        list.clear();

        list.addAll(pillarEdge(centerPos, true, true, 5 + layer));
        list.addAll(pillarEdge(centerPos, true, false, 5 + layer));
        list.addAll(pillarEdge(centerPos, false, true, 5 + layer));
        list.addAll(pillarEdge(centerPos, false, false, 5 + layer));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.CHISELED));
        }
        list.clear();

        // wall window part
        list.addAll(wallWindowTop(centerPos, true, true, 5 + layer));
        list.addAll(wallWindowTop(centerPos, true, false, 5 + layer));
        list.addAll(wallWindowTop(centerPos, false, true, 5 + layer));
        list.addAll(wallWindowTop(centerPos, false, false, 5 + layer));

        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH));
        }
        list.clear();

        wallWindowInnerTopStairs(player, centerPos, true, true, 3 + layer);
        wallWindowInnerTopStairs(player, centerPos, true, false, 3 + layer);
        wallWindowInnerTopStairs(player, centerPos, false, true, 3 + layer);
        wallWindowInnerTopStairs(player, centerPos, false, false, 3 + layer);

        wallWindowOuterTopStairs(player, centerPos, true, true, 4 + layer);
        wallWindowOuterTopStairs(player, centerPos, true, false, 4 + layer);
        wallWindowOuterTopStairs(player, centerPos, false, true, 4 + layer);
        wallWindowOuterTopStairs(player, centerPos, false, false, 4 + layer);
        list.addAll(cardinalBasicEdge(centerPos, 4 + layer, 0));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("stone_slab").getBlockState().getBaseState()
                    .withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND));
        }
        list.clear();

        list.addAll(cardinalBasicEdge(centerPos, 4 + layer, 1));
        list.addAll(wallWindowInnerBottom(centerPos, true, true, 4 + layer));
        list.addAll(wallWindowInnerBottom(centerPos, true, false, 4 + layer));
        list.addAll(wallWindowInnerBottom(centerPos, false, true, 4 + layer));
        list.addAll(wallWindowInnerBottom(centerPos, false, false, 4 + layer));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone").getBlockState().getBaseState());
        }
        list.clear();

        list.addAll(wallWindowInnerPillar(centerPos, true, true, 1 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, false, 1 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, true, 1 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, false, 1 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, true, 2 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, false, 2 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, true, 2 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, false, 2 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, true, 3 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, false, 3 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, true, 3 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, false, 3 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, true, 4 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, true, false, 4 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, true, 4 + layer));
        list.addAll(wallWindowInnerPillar(centerPos, false, false, 4 + layer));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH));
        }
        list.clear();

        list.addAll(cardinalBasicEdge(centerPos, 3 + layer, 1));
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("stone_slab").getBlockState().getBaseState()
                    .withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND));
        }
        list.clear();
    }

    static void innerSeatBlocks(EntityPlayer player, BlockPos centerPos) {
        int offset = 0;
        for (int i = 15; i >= 13; i--) {
            innerWoodSeats(player, centerPos, true, true, i, offset, false);
            innerWoodSeats(player, centerPos, true, true, i - 1, offset, true);
            innerWoodSeats(player, centerPos, true, false, i, offset, false);
            innerWoodSeats(player, centerPos, true, false, i - 1, offset, true);
            innerWoodSeats(player, centerPos, false, true, i, offset, false);
            innerWoodSeats(player, centerPos, false, true, i - 1, offset, true);
            innerWoodSeats(player, centerPos, false, false, i, offset, false);
            innerWoodSeats(player, centerPos, false, false, i - 1, offset, true);
            offset++;
        }
        List<BlockPos> list = new ArrayList<BlockPos>();
        for (int i = 11; i >= 9; i--) {
            list.addAll(innerSeatWall(player, centerPos, true, true, i, offset - 1));
            list.addAll(innerSeatWall(player, centerPos, true, false, i, offset - 1));
            list.addAll(innerSeatWall(player, centerPos, false, true, i, offset - 1));
            list.addAll(innerSeatWall(player, centerPos, false, false, i, offset - 1));
        }
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("sandstone")
                    .getBlockState()
                    .getBaseState()
                    .withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH));
        }
        list.clear();

        for (int i = 8; i >= 4; i--) {
            innerWoodSeats(player, centerPos, true, true, i, offset, false);
            innerWoodSeats(player, centerPos, true, true, i - 1, offset, true);
            innerWoodSeats(player, centerPos, true, false, i, offset, false);
            innerWoodSeats(player, centerPos, true, false, i - 1, offset, true);
            innerWoodSeats(player, centerPos, false, true, i, offset, false);
            innerWoodSeats(player, centerPos, false, true, i - 1, offset, true);
            innerWoodSeats(player, centerPos, false, false, i, offset, false);
            innerWoodSeats(player, centerPos, false, false, i - 1, offset, true);
            offset++;
        }

        for (int i = 3; i >= 0; i--) {
            list.addAll(innerSeatWall(player, centerPos, true, true, i, offset));
            list.addAll(innerSeatWall(player, centerPos, true, false, i, offset));
            list.addAll(innerSeatWall(player, centerPos, false, true, i, offset));
            list.addAll(innerSeatWall(player, centerPos, false, false, i, offset));
        }
        for (BlockPos blockPos : list) {
            player.worldObj.setBlockState(blockPos, Block.getBlockFromName("stonebrick").getBlockState().getBaseState());
        }
        list.clear();
    }

    private static List<BlockPos> cardinalBasicEdge(BlockPos centerPos, int heightOffset, int offset) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        // the south edge
        list.add(new BlockPos(centerPos.getX(), centerPos.getY() + heightOffset, centerPos.getZ() - (22 - offset)));
        // the north edge
        list.add(new BlockPos(centerPos.getX(), centerPos.getY() + heightOffset, centerPos.getZ() + (22 - offset)));

        // the west edge
        list.add(new BlockPos(centerPos.getX() - (17 - offset), centerPos.getY() + heightOffset, centerPos.getZ()));
        // the east edge
        list.add(new BlockPos(centerPos.getX() + (17 - offset), centerPos.getY() + heightOffset, centerPos.getZ()));
        return list;
    }

    private static List<BlockPos> quarterWallEdge(BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        return list;
    }

    private static List<BlockPos> pillarEdge(BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;
        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;
        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        ++i;
        --j;
        --j;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        ++i;
        --j;
        --j;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        --j;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        --j;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;

        return list;
    }

    private static List<BlockPos> wallWindowTop(BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        list.addAll(cardinalBasicEdge(centerPos, heightOffset, 0));
        list.addAll(quarterWallEdge(centerPos, centerToRight, downToTop, heightOffset));
        list.removeAll(pillarEdge(centerPos, centerToRight, downToTop, heightOffset));


        return list;
    }

    private static List<BlockPos> wallWindowInnerBottom(BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        ++i;

        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        --j;
        ++i;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        return list;
    }

    private static void wallWindowInnerTopStairs(EntityPlayer player, BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {

        IBlockState blockState_horizontal = Block.getBlockFromName("sandstone_stairs").getBlockState().getBaseState()
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        if (centerToRight) {
            blockState_horizontal = blockState_horizontal.withProperty(BlockStairs.FACING, EnumFacing.EAST);
        } else {
            blockState_horizontal = blockState_horizontal.withProperty(BlockStairs.FACING, EnumFacing.WEST);
        }

        IBlockState blockState_vertical = Block.getBlockFromName("sandstone_stairs").getBlockState().getBaseState()
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);

        if (downToTop) {
            blockState_vertical = blockState_vertical.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
        } else {
            blockState_vertical = blockState_vertical.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
        }

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        ++i;

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_horizontal.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        ++i;

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_horizontal.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        ++i;

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_horizontal.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        ++i;

        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_horizontal.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        --j;
        ++i;

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_vertical.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_vertical.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_vertical.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_vertical.getValue(BlockStairs.FACING).toString()).getOpposite()));
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical
                .withProperty(BlockStairs.FACING, EnumFacing.byName(blockState_vertical.getValue(BlockStairs.FACING).toString()).getOpposite()));
    }

    private static void wallWindowOuterTopStairs(EntityPlayer player, BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {

        IBlockState blockState_vertical = Block.getBlockFromName("sandstone_stairs").getBlockState().getBaseState()
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        if (centerToRight) {
            blockState_vertical = blockState_vertical.withProperty(BlockStairs.FACING, EnumFacing.WEST);
        } else {
            blockState_vertical = blockState_vertical.withProperty(BlockStairs.FACING, EnumFacing.EAST);
        }

        IBlockState blockState_horizontal = Block.getBlockFromName("sandstone_stairs").getBlockState().getBaseState()
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        if (downToTop) {
            blockState_horizontal = blockState_horizontal.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
        } else {
            blockState_horizontal = blockState_horizontal.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
        }

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        ++i;

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        ++i;

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        ++i;

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        ++i;

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        --j;
        ++i;

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
    }

    private static List<BlockPos> wallWindowInnerPillar(BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;

        --j;
        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;
        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        ++i;
        ++i;

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        --j;

        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        ++i;
        --j;
        --j;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        --j;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;
        --j;

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        --j;

        return list;
    }

    private static void innerWoodSeats(EntityPlayer player, BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset, int offset, boolean reverse) {

        IBlockState blockState_vertical = Block.getBlockFromName("oak_stairs").getBlockState().getBaseState()
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        if (centerToRight && !reverse) {
            blockState_vertical = blockState_vertical.withProperty(BlockStairs.FACING, EnumFacing.EAST);
        } else {
            blockState_vertical = blockState_vertical.withProperty(BlockStairs.FACING, EnumFacing.WEST);
        }

        IBlockState blockState_horizontal = Block.getBlockFromName("oak_stairs").getBlockState().getBaseState()
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        if (downToTop && !reverse) {
            blockState_horizontal = blockState_horizontal.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
        } else {
            blockState_horizontal = blockState_horizontal.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
        }

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;

        j = j - offset;

        --j;
        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        --j;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_horizontal);

        i = i - offset;
        j = j + offset;

        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);

        ++i;
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
        player.worldObj.setBlockState(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j), blockState_vertical);
    }

    private static List<BlockPos> innerSeatWall(EntityPlayer player, BlockPos centerPos, boolean centerToRight, boolean downToTop, int heightOffset, int offset) {

        List<BlockPos> list = new ArrayList<BlockPos>();

        int horizontal = centerToRight ? 1 : -1;
        int vertical = downToTop ? 1 : -1;

        // the view is from bottom to right-middle
        int i = 0;
        int j = 22;

        j = j - offset;

        --j;
        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        --j;
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * ++i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));

        i = i - offset;
        j = j + offset;

        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        ++i;
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));
        list.add(new BlockPos(centerPos.getX() + horizontal * i, centerPos.getY() + heightOffset, centerPos.getZ() + vertical * --j));

        return list;
    }
}

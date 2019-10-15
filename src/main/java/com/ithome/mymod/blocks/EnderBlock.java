package com.ithome.mymod.blocks;

import com.ithome.mymod.MyMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class EnderBlock extends Block {
    public EnderBlock() {
        super(Material.iron);
        this.setUnlocalizedName("enderBlock");
        this.setResistance(5.0f);
        this.setHardness(10.0f);
        this.setLightLevel(1.0f);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            EntityEnderEye eye = new EntityEnderEye(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 1.5,
                    pos.getZ() + 0.5
            );
            player.addChatComponentMessage(new ChatComponentText(
                    EnumChatFormatting.DARK_PURPLE +
                            "EnderBlock Clicked!"
            ));
            // 讓終界之眼緩緩升起
            eye.motionY = 0.1;
            world.spawnEntityInWorld(eye);

            return true;
        } else {
            // 伺服器端，不做任何事
            return false;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        // 這個方塊掉落的物品為我們的藥丸
        return MyMod.pillItem;
    }


    @Override
    public int quantityDropped(Random random) {
        // 掉落數量為 [1, 3] 區間
        return random.nextInt(3) + 1;
    }
}

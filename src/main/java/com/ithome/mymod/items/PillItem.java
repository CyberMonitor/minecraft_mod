package com.ithome.mymod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PillItem extends ItemFood {
    public PillItem() {
        // 藥丸造成的飢餓值補充為1，而且此食物不能為狼所食用
        super(1, false);
        this.setUnlocalizedName("pillItem");
        // 設定藥丸的效果：食用後可以產生移動加速III的效果、持續60秒
//        this.setPotionEffect(Potion.moveSpeed.getId(), 60, 2, 1.0f);
        // 任何情況都可以食用
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote) {
            List<Integer> includes = new ArrayList<Integer>();
            includes.addAll(Arrays.asList(1, 3, 5, 6, 8, 10, 11, 12, 13));

            Potion[] potions = Potion.potionTypes;
            for (Potion potion : potions) {
                if(potion != null && includes.contains(potion.getId())) {
                    player.addPotionEffect(new PotionEffect(potion.getId(), 20 * 20, 2));
                }
            }
        }
    }
}
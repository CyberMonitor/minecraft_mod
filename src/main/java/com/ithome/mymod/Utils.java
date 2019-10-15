package com.ithome.mymod;

import net.minecraft.util.EnumChatFormatting;

import java.util.Random;

public class Utils {

    public static String rainbowString(String str) {
        int stringLength = str.length();
        if (stringLength < 1) {
            return "";
        }
        StringBuilder returnStr = new StringBuilder();
        Random r = new Random();

        EnumChatFormatting[] colorChar =
                {
                        EnumChatFormatting.RED,
                        EnumChatFormatting.GOLD,
                        EnumChatFormatting.YELLOW,
                        EnumChatFormatting.GREEN,
                        EnumChatFormatting.AQUA,
                        EnumChatFormatting.BLUE,
                        EnumChatFormatting.LIGHT_PURPLE,
                        EnumChatFormatting.DARK_PURPLE
                };
        for (int i = 0; i < stringLength; i++) {
            returnStr.append(colorChar[r.nextInt(colorChar.length)]).append(str.substring(i, i + 1));
        }
        // return color to a common one after (most chat is white, but for other GUI might want black)
//        if (parReturnToBlack) {
//            return returnStr.toString() + EnumChatFormatting.BLACK;
//        }
        return returnStr.toString();
    }
}

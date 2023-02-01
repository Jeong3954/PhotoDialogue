package kr.glound03.pd.manager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class Utils {

    public static void sendMessage(EntityPlayer player, String message) {
        player.sendMessage(new TextComponentString(convertColor(message)));
    }

    public static void sendSystemMessage(EntityPlayer player, String message) {
        sendMessage(player, "&a&l[!] &7" + message);
    }

    public static void sendWarnMessage(EntityPlayer player, String message) {
        sendMessage(player, "&c&l[!] &7" + message);
    }

    public static String convertColor(String message) {
        return message.replace("&", "§");
    }
}

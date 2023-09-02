package me.math3w.itemcooldowns.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern hexColorPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private Utils() {
        throw new IllegalStateException("Cannot instantiate utility class");
    }

    public static String colorize(String message) {
        if (getMajorServerVersion() >= 16) {
            Matcher matcher = hexColorPattern.matcher(message);

            while (matcher.find()) {
                String color = message.substring(matcher.start(), matcher.end());
                Class<net.md_5.bungee.api.ChatColor> chatColorClass = net.md_5.bungee.api.ChatColor.class;

                try {
                    message = message.replace(color, "" + chatColorClass.getMethod("of", String.class).invoke(null, color));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                matcher = hexColorPattern.matcher(message);
            }
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static int getMajorServerVersion() {
        return Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
    }
}

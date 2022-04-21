package net.mixeration.AtomKey.incore;

import net.mixeration.AtomKey.core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class cloud {


    /*
        AtomVip - Console Logger Part
        Time & date: 22:00 | 12.03.2022
    */

    public static void succes(String message) {
        Bukkit.getLogger().fine(message);
    }

    public static void error(String message) {
        Bukkit.getLogger().warning(message);
    }

    public static void message(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /*
        AtomVip - File Part
        Time & date: 22:03 | 12.03.22
     */

    public static File messageFile;
    public static FileConfiguration messageConfig;

    public static void createMessage() {
        messageFile = new File(core.getInstance().getDataFolder(), "messages.yml");
        if (!messageFile.exists()) {
            messageFile.getParentFile().mkdirs();
            core.getInstance().saveResource("messages.yml", false);
        }
        messageConfig = new YamlConfiguration();
        try {
            messageConfig.load(messageFile);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public static FileConfiguration getMessage() {
        return messageConfig;
    }

    public static void saveLanguage() {
        core.getInstance().getServer().getScheduler().runTask(core.getInstance(), () ->{
            File fileKeys = new File(core.getInstance().getDataFolder(), "messages.yml");
            try {
                messageConfig.save(fileKeys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static File keyFile;
    public static FileConfiguration keyConfig;

    public static void createKeys() {
        keyFile = new File(core.getInstance().getDataFolder(), "keys.yml");
        if (!keyFile.exists()) {
            keyFile.getParentFile().mkdirs();
            core.getInstance().saveResource("keys.yml", false);
        }
        keyConfig = new YamlConfiguration();
        try {
            keyConfig.load(keyFile);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public static FileConfiguration getKeys() {
        return keyConfig;
    }

    public static void saveKeys() {
        core.getInstance().getServer().getScheduler().runTask(core.getInstance(), () ->{
            File fileKeys = new File(core.getInstance().getDataFolder(), "keys.yml");
            try {
                keyConfig.save(fileKeys);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void loadConfig() {
        FileConfiguration config = core.getInstance().getConfig();
        new File(core.getInstance().getDataFolder(), "config.yml");
        core.getInstance().saveDefaultConfig();
    }

    /*
        AtomVip - Some player modules
        Time & date: 9:43 | 13.03.2022
     */

    public static Player atomUV() {
        return atomUV().getPlayer();
    }

    public static void atomMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String generateKey(int length) {
        char[] chartset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(chartset.length);
            result[i] = chartset[randomCharIndex];
        }
        return new String(result);
    }

    public static String getKey(String key) {
        return getKeys().getString("keys." + key + ".keyID." + key);
    }

    public static boolean isKeyUsed(String key) {
        if(getKeys().getString("keys." + key + ".isUsed").equalsIgnoreCase("true")) {
            return true;
        } else if(getKeys().getString("keys." + key + ".isUsed").equalsIgnoreCase("false")) {
            return false;
        }
        return false;
    }

    /*
        AtomVip - Plugin modules
        Time & date: 11:59 | 13.03.2022
     */

    public static String version() {
        return core.getInstance().getServer().getPluginManager().getPlugin("AtomKey").getDescription().getVersion();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}

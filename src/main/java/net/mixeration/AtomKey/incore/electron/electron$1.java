package net.mixeration.AtomKey.incore.electron;

import net.mixeration.AtomKey.core;
import net.mixeration.AtomKey.incore.cloud;
import net.mixeration.AtomKey.outcore.SongodaAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

import static net.mixeration.AtomKey.incore.cloud.*;
import static net.mixeration.AtomKey.incore.notron.notron$1.checkUpdates;

public class electron$1 implements CommandExecutor {
    int pluginID = 802;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if(command.getName().equalsIgnoreCase("AtomKey")) {
                if(args.length == 0) {
                    for (String help : cloud.getMessage().getStringList("messages.help-console")) {
                        help = help.replace("%version%", cloud.version());
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', help));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    cloud.saveKeys();
                    cloud.saveLanguage();
                    core.getInstance().reloadConfig();
                    core.getInstance().saveConfig();
                    message("&dAtomKey &7Plugin reloaded.");
                    return true;
                } else if (args[0].equalsIgnoreCase("checkupdate")) {
                    checkUpdates(pluginID);
                    return true;
                } else if (args[0].equalsIgnoreCase("about")) {
                    message("&dAtomKey &7- &fStats");
                    message("   &7- Download: " + SongodaAPI.getDownloads(String.valueOf(pluginID)));
                    message("   &7- Views: " + SongodaAPI.getViews(String.valueOf(pluginID)));
                    message("   &7- Last version: " + SongodaAPI.getLastVersion(String.valueOf(pluginID)));
                    message("   &7- Rating: " + SongodaAPI.getRating(String.valueOf(pluginID)));
                    return true;
                }
            }
        } else {
            Player atomUV = ((Player) sender).getPlayer();
            if (command.getName().equalsIgnoreCase("AtomKey")) {
                if (args.length <= 0 || args[0].equalsIgnoreCase("help")) {
                    for (String help : cloud.getMessage().getStringList("messages.help")) {
                        help = help.replace("%version%", cloud.version());
                        atomUV.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("listkeys")) {
                    if(atomUV.hasPermission("atomkey.listkeys")) {
                        Set<String> keys = getKeys().getConfigurationSection("keys").getKeys(false);
                        for (String key : keys) {
                            String value = getKeys().getString("keys." + key);
                            value = value.replace("MemorySection", "").replace("[", "").replace("path", "").replace("]", "").replace("=", "").replace("'", "").replace("keys.", "").replace(",", "").replace("root", "").replace("YamlConfiguration", "");
                            atomMessage(atomUV, cloud.getMessage().getString("messages.keys").replace("%keys%", value));
                        }
                        return true;
                    } else {
                        atomMessage(atomUV, cloud.getMessage().getString("messages.no-permission"));
                        return true;
                    }
                } else if (args.length > 1) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("testkey")) {
                            if (args[1].equalsIgnoreCase(cloud.getKeys().getString("keys." + args[1] + ".keyID"))) {
                                if (!(cloud.getKeys().getInt("keys." + args[1] + ".duration") > 1)) {
                                    if (cloud.getKeys().getString("keys." + args[1] + ".isUsed").equalsIgnoreCase("false")) {
                                        String runcommand = cloud.getKeys().getString("keys." + args[1] + ".to-do");
                                        runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                        cloud.getKeys().set("keys." + args[1] + ".duration", cloud.getKeys().getInt("keys." + args[1] + ".duration") - 1);
                                        cloud.saveKeys();
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.key-active-test"));
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                        return true;
                                    } else {
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                        return true;
                                    }
                                } else if (cloud.getKeys().getInt("keys." + args[1] + ".duration") == 1) {
                                    if (cloud.getKeys().getString("keys." + args[1] + ".isUsed").equalsIgnoreCase("false")) {
                                        String runcommand = cloud.getKeys().getString("keys." + args[1] + ".to-do");
                                        runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                        cloud.getKeys().set("keys." + args[1] + ".isUsed", "true");
                                        cloud.getKeys().set("keys." + args[1] + ".duration", cloud.getKeys().getInt("keys." + args[1] + ".duration") - 1);
                                        cloud.saveKeys();
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                        return true;
                                    } else {
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                        return true;
                                    }
                                }
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.key-not-found"));
                                return true;
                            }
                        } else if (args[0].equalsIgnoreCase("removekey")) {
                            if (atomUV.hasPermission("atomkey.removekey")) {
                                if (args[1].equalsIgnoreCase(cloud.getKeys().getString("keys." + args[1] + ".keyID"))) {
                                    cloud.getKeys().set("keys." + args[1], null);
                                    atomMessage(atomUV, cloud.getMessage().getString("messages.key-removed"));
                                    cloud.saveKeys();
                                } else {
                                    atomMessage(atomUV, cloud.getMessage().getString("messages.error.key-not-found"));
                                    return true;
                                }
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.no-permission"));
                                return true;
                            }
                        } else if (args[0].equalsIgnoreCase("changeordinary")) {
                            if (atomUV.hasPermission("atomkey.changeordinary")) {
                                if (args[1].equalsIgnoreCase(cloud.getKeys().getString("keys." + args[1] + ".keyID"))) {
                                    if(cloud.getKeys().getString("keys." + args[1] + ".ordinary").equalsIgnoreCase("common")) {
                                        cloud.getKeys().set("keys." + args[1] + ".ordinary", "rare");
                                        saveKeys();
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.ordinary-changed").replace("%key%", args[1]).replace("%new_ordinary%", cloud.getKeys().getString("keys." + args[1] + ".ordinary")));
                                        return true;
                                    } else if(cloud.getKeys().getString("keys." + args[1] + ".ordinary").equalsIgnoreCase("rare")) {
                                        cloud.getKeys().set("keys." + args[1] + ".ordinary", "legendary");
                                        saveKeys();
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.ordinary-changed").replace("%key%", args[1]).replace("%new_ordinary%", cloud.getKeys().getString("keys." + args[1] + ".ordinary")));
                                        return true;
                                    } else if(cloud.getKeys().getString("keys." + args[1] + ".ordinary").equalsIgnoreCase("legendary")) {
                                        cloud.getKeys().set("keys." + args[1] + ".ordinary", "common");
                                        saveKeys();
                                        atomMessage(atomUV, cloud.getMessage().getString("messages.ordinary-changed").replace("%key%", args[1]).replace("%new_ordinary%", cloud.getKeys().getString("keys." + args[1] + ".ordinary")));
                                        return true;
                                    }
                                    cloud.saveKeys();
                                    return true;
                                } else {
                                    atomMessage(atomUV, cloud.getMessage().getString("messages.error.key-not-found"));
                                    return true;
                                }
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.no-permission"));
                                return true;
                            }
                        }
                    } else if (args.length == 3) {
                        String to_do = args[1];
                        to_do = to_do.replace("_", " ");
                        if (args[0].equalsIgnoreCase("generatekey")) {
                            if (atomUV.hasPermission("atomkey.generatekey")) {
                                if (isInteger(args[2])) {
                                    String key_in_list = cloud.generateKey(core.getInstance().getConfig().getInt("options.default-key-lenght"));
                                    atomUV.sendMessage(ChatColor.translateAlternateColorCodes('&', cloud.getMessage().getString("messages.key-generated").replace("%key%", key_in_list)));
                                    getKeys().set("keys." + key_in_list, null);
                                    getKeys().set("keys." + key_in_list + ".keyID", null);
                                    getKeys().set("keys." + key_in_list + ".keyID", key_in_list);
                                    getKeys().set("keys." + key_in_list + ".duration", null);
                                    getKeys().set("keys." + key_in_list + ".duration", args[2]);
                                    getKeys().set("keys." + key_in_list + ".to-do", null);
                                    getKeys().set("keys." + key_in_list + ".to-do", to_do);
                                    getKeys().set("keys." + key_in_list + ".isUsed", null);
                                    getKeys().set("keys." + key_in_list + ".isUsed", "false");
                                    getKeys().set("keys." + key_in_list + ".ordinary", null);
                                    getKeys().set("keys." + key_in_list + ".ordinary", "common");
                                    saveKeys();
                                    return true;
                                } else {
                                    atomMessage(atomUV, cloud.getMessage().getString("messages.must-be-number"));
                                    return true;
                                }
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.no-permission"));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}

/*
Run command and message | Burayı vault kullanarak grup atayarakda yapabilirim
Ancak şu anlık komut ekleyeceğim. Bunu yaptıktan sonra vault apiyi ekleyeceğim.
Vault ekonomisi için bir şeyler düşünmem gerek.
ve anahtarın kullanılma durumunu `kullanıldı` olarak ayarlanacak.
*/

package net.mixeration.AtomKey.incore.electron;

import net.mixeration.AtomKey.incore.cloud;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mixeration.AtomKey.incore.cloud.atomMessage;
import static net.mixeration.AtomKey.incore.cloud.message;

public class electron$2 implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player atomUV = ((Player) sender).getPlayer();
            if (command.getName().equalsIgnoreCase("Key")) {
                if (args[0].equalsIgnoreCase(cloud.getKeys().getString("keys." + args[0] + ".keyID"))) {
                    if (!(cloud.getKeys().getInt("keys." + args[0] + ".duration") > 1)) {
                        if(cloud.getKeys().getString("keys." + args[0] + ".ordinary").equalsIgnoreCase("common")) {
                            if (cloud.getKeys().getString("keys." + args[0] + ".isUsed").equalsIgnoreCase("false")) {
                                String runcommand = cloud.getKeys().getString("keys." + args[0] + ".to-do");
                                runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                cloud.getKeys().set("keys." + args[0] + ".duration", cloud.getKeys().getInt("keys." + args[0] + ".duration") - 1);
                                cloud.saveKeys();
                                atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                return true;
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                return true;
                            }
                        } else if(cloud.getKeys().getString("keys." + args[0] + ".ordinary").equalsIgnoreCase("rare")) {
                            if (cloud.getKeys().getString("keys." + args[0] + ".isUsed").equalsIgnoreCase("false")) {
                                String runcommand = cloud.getKeys().getString("keys." + args[0] + ".to-do");
                                runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                cloud.getKeys().set("keys." + args[0] + ".duration", cloud.getKeys().getInt("keys." + args[0] + ".duration") - 1);
                                cloud.saveKeys();
                                atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                return true;
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                return true;
                            }
                        } else if(cloud.getKeys().getString("keys." + args[0] + ".ordinary").equalsIgnoreCase("legendary")) {
                            if (cloud.getKeys().getString("keys." + args[0] + ".isUsed").equalsIgnoreCase("false")) {
                                String runcommand = cloud.getKeys().getString("keys." + args[0] + ".to-do");
                                runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                cloud.getKeys().set("keys." + args[0] + ".duration", cloud.getKeys().getInt("keys." + args[0] + ".duration") - 1);
                                cloud.saveKeys();
                                atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                return true;
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                return true;
                            }
                        }
                    } else if (cloud.getKeys().getInt("keys." + args[0] + ".duration") == 1) {
                        if (cloud.getKeys().getString("keys." + args[0] + ".ordinary").equalsIgnoreCase("common")) {
                            if (cloud.getKeys().getString("keys." + args[0] + ".isUsed").equalsIgnoreCase("false")) {
                                String runcommand = cloud.getKeys().getString("keys." + args[0] + ".to-do");
                                runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                cloud.getKeys().set("keys." + args[0] + ".isUsed", "true");
                                cloud.getKeys().set("keys." + args[0] + ".duration", cloud.getKeys().getInt("keys." + args[0] + ".duration") - 1);
                                cloud.saveKeys();
                                atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                atomUV.getWorld().spawnParticle(Particle.SMOKE_LARGE, atomUV.getLocation(), 1);
                                return true;
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                return true;
                            }
                        } else if (cloud.getKeys().getString("keys." + args[0] + ".ordinary").equalsIgnoreCase("rare")) {
                            if (cloud.getKeys().getString("keys." + args[0] + ".isUsed").equalsIgnoreCase("false")) {
                                String runcommand = cloud.getKeys().getString("keys." + args[0] + ".to-do");
                                runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                cloud.getKeys().set("keys." + args[0] + ".isUsed", "true");
                                cloud.getKeys().set("keys." + args[0] + ".duration", cloud.getKeys().getInt("keys." + args[0] + ".duration") - 1);
                                cloud.saveKeys();
                                atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                atomUV.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, atomUV.getLocation(), 1);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                return true;
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                return true;
                            }
                        } else if (cloud.getKeys().getString("keys." + args[0] + ".ordinary").equalsIgnoreCase("legendary")) {
                            if (cloud.getKeys().getString("keys." + args[0] + ".isUsed").equalsIgnoreCase("false")) {
                                String runcommand = cloud.getKeys().getString("keys." + args[0] + ".to-do");
                                runcommand = runcommand.replace("+player", atomUV.getPlayer().getName());
                                cloud.getKeys().set("keys." + args[0] + ".isUsed", "true");
                                cloud.getKeys().set("keys." + args[0] + ".duration", cloud.getKeys().getInt("keys." + args[0] + ".duration") - 1);
                                cloud.saveKeys();
                                atomMessage(atomUV, cloud.getMessage().getString("messages.key-active").replace("%ordinary%", cloud.getKeys().getString("keys." + args[0] + ".ordinary")));
                                atomUV.getWorld().spawnParticle(Particle.TOTEM, atomUV.getLocation(), 1);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), runcommand);
                                return true;
                            } else {
                                atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                                return true;
                            }
                        }
                    }else {
                        atomMessage(atomUV, cloud.getMessage().getString("messages.error.used-key"));
                        return true;
                    }
                } else {
                    atomMessage(atomUV, cloud.getMessage().getString("messages.error.key-not-found"));
                    return true;
                }
            }
        } else {
            message(cloud.getMessage().getString("messages.error.no-console"));
            return true;
        }
        return false;
    }
}

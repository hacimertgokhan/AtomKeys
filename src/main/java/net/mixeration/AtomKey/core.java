package net.mixeration.AtomKey;

import net.mixeration.AtomKey.incore.electron.electron$1;
import net.mixeration.AtomKey.incore.electron.electron$2;
import org.bukkit.plugin.java.JavaPlugin;

import static net.mixeration.AtomKey.incore.cloud.*;
import static net.mixeration.AtomKey.outcore.VaultAPI.*;

public final class core extends JavaPlugin {
    private static core instance;
    public static synchronized core getInstance() {
        return instance;
    }
    public static synchronized void setInstance(core mixeration) {
        instance = mixeration;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        createMessage();
        createKeys();
        loadConfig();
        getCommand("AtomKey").setExecutor(new electron$1());
        getCommand("Key").setExecutor(new electron$2());
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();

    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
}

package me.kapehh.net.cfactoryfractions;

import me.kapehh.main.pluginmanager.config.EventPluginConfig;
import me.kapehh.main.pluginmanager.config.EventType;
import me.kapehh.main.pluginmanager.config.PluginConfig;
import me.kapehh.main.pluginmanager.vault.PluginVault;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by karen on 25.08.2016.
 */
public class Main extends JavaPlugin {
    public static Permission permission;

    @EventPluginConfig(EventType.LOAD)
    public void onLoadConfig(PluginConfig pc, FileConfiguration cfg) {
        ConfigParam.MESSAGE_MAYOR = pc.getColoredText("message-mayor");
        ConfigParam.MESSAGE_RESIDENT = pc.getColoredText("message-resident");
    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PluginManager") == null) {
            getLogger().info("PluginManager not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        permission = PluginVault.setupPermissions();

        PluginConfig pluginConfig = new PluginConfig(this, "config");
        pluginConfig.addDefault("message-mayor", "&cForbidden invite players from the opposing faction!")
                    .addDefault("message-resident", "&cYou can't join to town opposite faction!");
        pluginConfig.setEventListeners(this).setup();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {

    }
}

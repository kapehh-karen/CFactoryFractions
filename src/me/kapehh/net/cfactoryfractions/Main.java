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
/*    private PluginConfig pluginConfig;

    @EventPluginConfig(EventType.LOAD)
    public void onLoadConfig(FileConfiguration cfg) {
        ConfigParam.HEROUES_PREFiX = pluginConfig.getColoredText("heroes-prefix");
        ConfigParam.OUTCAST_PREFiX = pluginConfig.getColoredText("outcast-prefix");
        ConfigParam.NONAME_PREFiX = pluginConfig.getColoredText("noname-prefix");
    }

    @EventPluginConfig(EventType.DEFAULT)
    public void onDefaultConfig(FileConfiguration cfg) {
        cfg.set("heroes-prefix", "&e");
        cfg.set("outcast-prefix", "&c");
        cfg.set("noname-prefix", "&7");
    }*/

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PluginManager") == null) {
            getLogger().info("PluginManager not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        permission = PluginVault.setupPermissions();

        /*pluginConfig = new PluginConfig(this, "config");
        pluginConfig.addEventClasses(this).setup();*/

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {

    }
}

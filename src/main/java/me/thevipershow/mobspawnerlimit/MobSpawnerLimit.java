package me.thevipershow.mobspawnerlimit;

import me.thevipershow.mobspawnerlimit.commands.CommandListener;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.listeners.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class MobSpawnerLimit extends JavaPlugin {

    public static Plugin plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        logger = this.getLogger();
        Values values = new Values();
        values.setValues(getConfig());
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginCommand("msl").setExecutor(new CommandListener());
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}

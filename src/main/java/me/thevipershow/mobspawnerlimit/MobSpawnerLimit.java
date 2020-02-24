package me.thevipershow.mobspawnerlimit;

import me.thevipershow.mobspawnerlimit.commands.CommandListener;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.listeners.BlockPlaceListener;
import me.thevipershow.mobspawnerlimit.runnables.ChunkInspector;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public final class MobSpawnerLimit extends JavaPlugin {

    public static Plugin plugin;
    public static Logger logger;
    public static final Set<Chunk> chunks = new HashSet<>();


    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        logger = this.getLogger();
        Values values = new Values();
        values.setValues(getConfig());
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("msl")).setExecutor(new CommandListener());
        Bukkit.getScheduler().runTaskTimer(this, new ChunkInspector(), 20L, 10L);
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}

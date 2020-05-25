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
    public final Set<Chunk> chunks = new HashSet<>();
    private Values values;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        logger = this.getLogger();
        values = Values.getInstance(this);
        Bukkit.getPluginManager().registerEvents(BlockPlaceListener.getInstance(values, chunks), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("msl")).setExecutor(CommandListener.getInstance(values));
        Bukkit.getScheduler().runTaskTimer(this, new ChunkInspector(values, chunks), 20L, 10L);
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}

package me.thevipershow.mobspawnerlimit;

import java.util.HashSet;
import java.util.Set;
import me.thevipershow.mobspawnerlimit.checks.ChunkChecker;
import me.thevipershow.mobspawnerlimit.commands.MSLCommand;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.enums.Messages;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobSpawnerLimit extends JavaPlugin implements Listener {

    private Values values;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        saveDefaultConfig();
        values = Values.getInstance(this);
        values.updateValues();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("msl").setExecutor(MSLCommand.getInstance(values));
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (values.isEnabled()) {
            final Player player = event.getPlayer();
            final Block block = event.getBlock();
            final Chunk chunk = block.getChunk();
            final int currentLimit = values.getLimit();
            final long chunkAmount = ChunkChecker.check(chunk);
            if (chunkAmount + 1 > currentLimit) {
                event.setCancelled(true);
                values.getMessages()
                        .stream()
                        .map(s -> s
                                .replaceAll("%PREFIX%", Messages.PREFIX.getString())
                                .replaceAll("%MAX%", String.valueOf(currentLimit)))
                        .forEachOrdered(
                                s -> player.sendMessage(Messages.color(s))
                        );
            }
        }
    }
}

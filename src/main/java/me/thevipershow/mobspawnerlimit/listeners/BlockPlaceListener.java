package me.thevipershow.mobspawnerlimit.listeners;

import java.util.Set;
import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public final class BlockPlaceListener implements Listener {
    private Player player;
    private final Material spawner = Material.SPAWNER;
    private final Values values;
    private static BlockPlaceListener instance = null;

    private BlockPlaceListener(final Values values, final Set<Chunk> chunkSet) {
        this.values = values;
        this.chunkSet = chunkSet;
    }
    public static BlockPlaceListener getInstance(final Values values, final Set<Chunk> chunkSet) {
        return instance == null ? (instance = new BlockPlaceListener(values,chunkSet)) : instance;
    }

    private final Set<Chunk> chunkSet;

    @EventHandler
    public final void event(final BlockPlaceEvent event) {
        if (values.isEnabled()) {
            final Block block = event.getBlock();
            if (block.getType().equals(spawner)) {
                player = event.getPlayer();
                final Chunk chunk = block.getChunk();
                if (!chunkSet.contains(chunk)) {
                    if (Utils.chunkHasMaterial(spawner, chunk) > values.getLimit() - 1) {
                        event.setCancelled(true);
                        values.getMessages().forEach(message -> player.sendMessage(Utils.color(message)));
                        chunkSet.add(chunk);
                    }
                }
            }
        }
    }


}

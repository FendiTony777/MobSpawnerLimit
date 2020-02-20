package me.thevipershow.mobspawnerlimit.listeners;

import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    private Chunk chunk;
    private Block block;
    private Player player;
    private final Material spawner = Material.SPAWNER;

    private final Values values = new Values();

    @EventHandler
    public void event(BlockPlaceEvent event) {
        if (values.isEnabled()) {
            block = event.getBlock();
            if (block.getType().equals(spawner)) {
                player = event.getPlayer();
                chunk = block.getChunk();
                if (Utils.chunkHasMaterial(spawner, chunk) > values.getLimit()-1) {
                    event.setCancelled(true);
                    values.getMessages().forEach(message -> player.sendMessage(Utils.color(message)));
                }
            }
        }
    }


}

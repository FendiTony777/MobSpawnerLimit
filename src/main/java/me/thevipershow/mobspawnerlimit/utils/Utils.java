package me.thevipershow.mobspawnerlimit.utils;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;

public class Utils {

    public static int chunkHasMaterial(Material material, Chunk chunk) {
        int targetBlocksCounter = 0;
        for (BlockState blockState : chunk.getTileEntities()) {
            if (blockState.getType().equals(material)) {
                targetBlocksCounter++;
            }
        }
        return targetBlocksCounter;
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}

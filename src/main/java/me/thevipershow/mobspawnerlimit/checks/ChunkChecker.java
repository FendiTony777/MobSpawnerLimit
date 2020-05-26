package me.thevipershow.mobspawnerlimit.checks;

import java.util.Arrays;
import org.bukkit.Chunk;
import org.bukkit.Material;

public final class ChunkChecker {
    public static long check(final Chunk chunk) {
        return Arrays.stream(chunk.getTileEntities())
                .filter(te -> te.getType() == Material.SPAWNER)
                .count();
    }
}

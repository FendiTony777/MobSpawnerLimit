package me.thevipershow.mobspawnerlimit.runnables;

import java.util.Set;
import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;

public final class ChunkInspector implements Runnable {

    private final Values values;
    private final Set<Chunk> chunkSet;

    public ChunkInspector(final Values values, final Set<Chunk> chunkSet) {
        this.values = values;
        this.chunkSet = chunkSet;
    }

    @Override
    public void run() {
        chunkSet.forEach(chunk -> {
            if (Utils.chunkHasMaterial(Material.SPAWNER, chunk) < values.getLimit() - 1) {
                chunkSet.remove(chunk);
            }
        });
    }
}

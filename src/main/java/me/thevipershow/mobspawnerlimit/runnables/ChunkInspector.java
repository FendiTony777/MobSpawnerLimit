package me.thevipershow.mobspawnerlimit.runnables;

import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.utils.Utils;
import org.bukkit.Material;

public class ChunkInspector implements Runnable {

    Values values = new Values();

    @Override
    public void run() {
        MobSpawnerLimit.chunks.forEach(chunk -> {
            if (Utils.chunkHasMaterial(Material.SPAWNER, chunk) < values.getLimit() - 1) {
                MobSpawnerLimit.chunks.remove(chunk);
            }
        });
    }
}

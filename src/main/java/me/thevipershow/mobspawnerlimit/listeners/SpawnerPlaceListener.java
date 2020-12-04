package me.thevipershow.mobspawnerlimit.listeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.chat.ChatUtils;
import me.thevipershow.mobspawnerlimit.config.ConfigContext;
import me.thevipershow.mobspawnerlimit.config.ConfigNode;
import me.thevipershow.mobspawnerlimit.config.TomlConfiguration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

public final class SpawnerPlaceListener implements Listener {

    public SpawnerPlaceListener() {
        for (World world : MobSpawnerLimit.INSTANCE.getMobSpawnerLimit().getServer().getWorlds()) {
            this.worldLoadedBoundingBoxes.put(world.getUID(), new HashSet<>());
        }
    }

    private final HashMap<UUID, Set<BoundingBox>> worldLoadedBoundingBoxes = new HashMap<>();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public final void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        World blockWorld = block.getWorld();

        if (isContainedInAnyBoundingBox(block) || checkForNearbySpawners(block) > 0) {
            List<String> messagesToSend = MobSpawnerLimit.INSTANCE.getLoadedTomlConfigs().get(ConfigContext.MAIN).getValue(ConfigNode.CHECKS_MESSAGES);
            if (messagesToSend == null) {
                return;
            }
            messagesToSend.forEach(str -> player.sendMessage(ChatUtils.color(ChatUtils.replaceAllPlaceholdersInString(str))));
        } else {
            Set<BoundingBox> boundingBoxes = this.worldLoadedBoundingBoxes.get(blockWorld.getUID());
            if (boundingBoxes == null) {
                return;
            }
            boundingBoxes.add(generateSpawnerBox(block));
        }
    }

    public final int checkForNearbySpawners(Block block) {
        World world = block.getWorld();
        TomlConfiguration mainConfig = MobSpawnerLimit.INSTANCE.getLoadedTomlConfigs().get(ConfigContext.MAIN);
        Location location = block.getLocation();
        int xDist = mainConfig.getValue(ConfigNode.CHECKS_R_X_DIST);
        int yDist = mainConfig.getValue(ConfigNode.CHECKS_R_Y_DIST);
        int zDist = mainConfig.getValue(ConfigNode.CHECKS_R_Z_DIST);
        int blockX = location.getBlockX();
        int blockY = location.getBlockY();
        int blockZ = location.getBlockZ();
        int xStart = blockX - xDist;
        int yStart = blockY - yDist;
        int zStart = blockZ - zDist;
        int xEnd = blockX + xDist;
        int yEnd = blockY + yDist;
        int zEnd = blockZ + zDist;
        int spawnersFound = 0;
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                for (int z = zStart; z < zEnd; z++) {
                    if (world.getBlockAt(x, y, z).getType() == Material.SPAWNER) {
                        spawnersFound += 1;
                    }
                }
            }
        }
        return spawnersFound;
    }

    public final boolean isContainedInAnyBoundingBox(Block block) {
        World world = block.getWorld();
        Set<BoundingBox> boundingBoxes = this.worldLoadedBoundingBoxes.get(world.getUID());
        if (boundingBoxes == null) {
            return false;
        }

        for (BoundingBox boundingBox : boundingBoxes) {
            if (boundingBox.contains(block.getBoundingBox())) {
                return true;
            }
        }

        return false;
    }

    @NotNull
    public final BoundingBox generateSpawnerBox(@NotNull Block block) {
        TomlConfiguration mainConfig = MobSpawnerLimit.INSTANCE.getLoadedTomlConfigs().get(ConfigContext.MAIN);
        int xDist = mainConfig.getValue(ConfigNode.CHECKS_R_X_DIST);
        int yDist = mainConfig.getValue(ConfigNode.CHECKS_R_Y_DIST);
        int zDist = mainConfig.getValue(ConfigNode.CHECKS_R_Z_DIST);

        Location blockLocation = block.getLocation();

        return BoundingBox.of(blockLocation, xDist, yDist, zDist);
    }

    @EventHandler(ignoreCancelled = true)
    public final void onWorldUnload(WorldUnloadEvent event) { // multiverse|viperverse support
        this.worldLoadedBoundingBoxes.remove(event.getWorld().getUID());
    }

    @EventHandler(ignoreCancelled = true)
    public final void onWorldLoad(WorldLoadEvent event) { // multiverse|viperverse support
        World world = event.getWorld();
        this.worldLoadedBoundingBoxes.putIfAbsent(world.getUID(), new HashSet<>());
    }
}

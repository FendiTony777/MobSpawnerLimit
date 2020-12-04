package me.thevipershow.mobspawnerlimit;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class MobSpawnerLimitPlugin extends JavaPlugin {

    @NotNull
    public final File getFromResourceName(@NotNull String name) throws IllegalArgumentException {
        File target = new File(getDataFolder(), name);
        if (!target.exists() || !target.isFile() || !target.canRead()) {
            throw new IllegalArgumentException(String.format("The resource %s was could not be loaded!", name));
        }
        return target;
    }

    @Override
    public final void onEnable() {
        MobSpawnerLimit.INSTANCE.startPlugin(this);
    }
}

package me.thevipershow.mobspawnerlimit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import me.thevipershow.mobspawnerlimit.config.ConfigContext;
import me.thevipershow.mobspawnerlimit.config.TomlConfiguration;
import me.thevipershow.mobspawnerlimit.listeners.ListenerType;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public enum MobSpawnerLimit {

    INSTANCE;

    private MobSpawnerLimitPlugin mobSpawnerLimitPlugin;
    private final Map<ConfigContext, TomlConfiguration> loadedTomlConfigs = new HashMap<>();

    @NotNull
    public final MobSpawnerLimitPlugin getMobSpawnerLimit() {
        return mobSpawnerLimitPlugin;
    }

    public final void startPlugin(@NotNull MobSpawnerLimitPlugin mobSpawnerLimitPlugin) {
        this.mobSpawnerLimitPlugin = Objects.requireNonNull(mobSpawnerLimitPlugin, "The plugin tried to enable with a null instance!");
        loadConfigurations();
        enableListeners();
        registerCommands();
    }

    private void loadConfigurations() {
        for (ConfigContext configContext : ConfigContext.values()) {
            String configName = configContext.getName();
            this.mobSpawnerLimitPlugin.saveResource(configName, false);
            this.loadedTomlConfigs.putIfAbsent(configContext, Objects.requireNonNull(configContext.build(), String.format("The config %s did not generate correctly!", configName)));
        }
    }

    @NotNull
    public final Map<ConfigContext, TomlConfiguration> getLoadedTomlConfigs() {
        return loadedTomlConfigs;
    }

    @NotNull
    public final MobSpawnerLimitPlugin getMobSpawnerLimitPlugin() {
        return mobSpawnerLimitPlugin;
    }

    private void enableListeners() {
        PluginManager pluginManager = this.mobSpawnerLimitPlugin.getServer().getPluginManager();
        for (ListenerType listenerType : ListenerType.values()) {
            pluginManager.registerEvents(listenerType.build(), this.mobSpawnerLimitPlugin);
        }
    }

    private void registerCommands() {

    }
}

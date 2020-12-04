package me.thevipershow.mobspawnerlimit.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import me.grison.jtoml.impl.Toml;
import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.MobSpawnerLimitPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TomlConfiguration extends Configuration {

    private final String name;
    private final Toml toml;
    private final ConfigContext configContext;
    private final ConfigNodes configNodes;

    public TomlConfiguration(@NotNull ConfigContext configContext, @NotNull ConfigNodes configNodes) {
        this.configContext = configContext;
        this.configNodes = configNodes;
        MobSpawnerLimitPlugin mobSpawnerLimitPlugin = Objects.requireNonNull(MobSpawnerLimit.INSTANCE.getMobSpawnerLimit(), "Tried to read a TOML config but the plugin still wasn't enabled!");
        this.name = Objects.requireNonNull(configContext.getName(), String.format("The file name for %s was null!", getClass().getSimpleName()));
        this.toml = Toml.parse(Objects.requireNonNull(mobSpawnerLimitPlugin.getFromResourceName(configContext.getName()), String.format("The file passed for %s was null", getClass().getSimpleName())));
    }

    @Override
    public void storeData() {
        HashMap<String, Object> data = super.getData();
        for (ConfigNode configNode : configNodes.getConfigNodes()) {
            String key = configNode.getKeyName();
            data.putIfAbsent(key, toml.getAs(key, configNode.getKeyType()));
        }
    }

    @Nullable
    public <T> T getValue(@NotNull ConfigNode configNode) {
        Map<String, Object> data = getData();
        if (data.containsKey(configNode.getKeyName())) {
            return (T) data.get(configNode.getKeyName()); // this should not be null, but it can be.
        } else {
            return null;
        }
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Toml getToml() {
        return this.toml;
    }

    @NotNull
    public final ConfigContext getConfigContext() {
        return this.configContext;
    }

    @NotNull
    public final ConfigNodes getConfigNodes() {
        return this.configNodes;
    }
}

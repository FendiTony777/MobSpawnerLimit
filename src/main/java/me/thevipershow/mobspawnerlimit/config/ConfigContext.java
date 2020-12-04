package me.thevipershow.mobspawnerlimit.config;

import org.jetbrains.annotations.NotNull;

public enum ConfigContext {
    MAIN("config.toml", MainConfiguration.class);

    private final String name;
    private final Class<? extends TomlConfiguration> configClass;

    ConfigContext(@NotNull String name, @NotNull Class<? extends TomlConfiguration> configClass) {
        this.name = name;
        this.configClass = ClassUtils.checkForEmptyConstructor(configClass);
    }

    @NotNull
    public final TomlConfiguration build() {
        return ClassUtils.buildWithEmptyConstructor(this.configClass);
    }

    @NotNull
    public final Class<? extends TomlConfiguration> getConfigClass() {
        return configClass;
    }

    @NotNull
    public final String getName() {
        return name;
    }
}

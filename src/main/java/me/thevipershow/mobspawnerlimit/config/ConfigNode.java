package me.thevipershow.mobspawnerlimit.config;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public enum ConfigNode {
    CONFIG_ID("config.id", int.class),
    CHECKS_ENABLE("checks.enable", boolean.class),
    CHECKS_TYPE("checks.type", String.class),
    CHECKS_MESSAGES("checks.messages", List.class),
    CHECKS_R_X_DIST("checks.radius.x-distance", int.class),
    CHECKS_R_Y_DIST("checks.radius.y-distance", int.class),
    CHECKS_R_Z_DIST("checks.radius.z-distance", int.class),
    CHECKS_CHUNK("checks.chunk", int.class);

    ConfigNode(@NotNull String keyName, @NotNull Class<?> keyType) {
        this.keyName = keyName;
        this.keyType = keyType;
    }

    private final String keyName;
    private final Class<?> keyType;

    @NotNull
    public final String getKeyName() {
        return keyName;
    }

    @NotNull
    public final Class<?> getKeyType() {
        return keyType;
    }
}

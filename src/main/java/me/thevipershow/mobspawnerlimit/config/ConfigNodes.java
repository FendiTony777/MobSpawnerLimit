package me.thevipershow.mobspawnerlimit.config;

import static me.thevipershow.mobspawnerlimit.config.ConfigNode.*;
import org.jetbrains.annotations.NotNull;

public enum ConfigNodes {
    MAIN(ConfigContext.MAIN,
            CONFIG_ID,
            CHECKS_ENABLE,
            CHECKS_TYPE,
            CHECKS_MESSAGES,
            CHECKS_R_X_DIST,
            CHECKS_R_Y_DIST,
            CHECKS_R_Z_DIST,
            CHECKS_CHUNK);

    ConfigNodes(@NotNull ConfigContext configContext, @NotNull ConfigNode... configNodes) {
        this.configNodes = configNodes;
        this.configContext = configContext;
    }

    private final ConfigContext configContext;
    private final ConfigNode[] configNodes;

    @NotNull
    public final ConfigContext getConfigContext() {
        return configContext;
    }

    @NotNull
    public final ConfigNode[] getConfigNodes() {
        return configNodes;
    }
}

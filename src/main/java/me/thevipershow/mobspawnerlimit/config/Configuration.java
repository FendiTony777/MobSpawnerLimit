package me.thevipershow.mobspawnerlimit.config;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public abstract class Configuration implements DataStorage, DataObtainer {

    private final HashMap<String, Object> data = new HashMap<>();

    @Override
    public @NotNull HashMap<String, Object> getData() {
        return this.data;
    }
}

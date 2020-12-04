package me.thevipershow.mobspawnerlimit.config;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface DataObtainer {

    /**
     * Get all of the data mapped with key, and object.
     * @return The stored data.
     */
    @NotNull
    HashMap<String, Object> getData();
}

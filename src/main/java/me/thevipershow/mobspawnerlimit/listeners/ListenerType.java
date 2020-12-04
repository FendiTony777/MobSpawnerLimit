package me.thevipershow.mobspawnerlimit.listeners;

import me.thevipershow.mobspawnerlimit.config.ClassUtils;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public enum ListenerType {

    SPAWNER_PLACE(SpawnerPlaceListener.class);

    private final Class<? extends Listener> listenerClass;

    ListenerType(@NotNull Class<? extends Listener> listenerClass) {
        this.listenerClass = listenerClass;
    }

    @NotNull
    public final Listener build() {
        return ClassUtils.buildWithEmptyConstructor(this.listenerClass);
    }
}

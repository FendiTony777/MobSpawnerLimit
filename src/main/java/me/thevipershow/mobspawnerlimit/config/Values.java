package me.thevipershow.mobspawnerlimit.config;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Values {
    private static Values instance = null;
    private final JavaPlugin plugin;
    private final FileConfiguration configuration;

    private Values(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configuration = plugin.getConfig();
    }

    public static Values getInstance(JavaPlugin plugin) {
        return instance != null ? instance : (instance = new Values(plugin));
    }

    private boolean enabled;
    private int limit;
    private List<String> messages;

    public final void updateValues() {
        plugin.reloadConfig();
        enabled = configuration.getBoolean("chunk.enabled");
        limit = configuration.getInt("chunk.limit");
        messages = configuration.getStringList("chunk.messages");
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
        configuration.set("chunk.enabled", enabled);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        configuration.set("chunk.limit", limit);
    }

    public List<String> getMessages() {
        return messages;
    }
}

package me.thevipershow.mobspawnerlimit.config;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.java.JavaPlugin;

public final class Values {
    private static Values instance = null;
    private final JavaPlugin plugin;
    private final FileConfiguration configuration;

    private Values(final JavaPlugin plugin) {
        this.plugin = plugin;
        this.configuration = this.plugin.getConfig();
    }

    public static Values getInstance(final JavaPlugin plugin) {
        return instance != null ? instance : (instance = new Values(plugin));
    }

    public final void updateValues() {
        plugin.reloadConfig();
        enabled = configuration.getBoolean("chunk.enabled");
        limit = configuration.getInt("chunk.limit");
        messages = configuration.getStringList("chunk.messages");
    }

    private boolean enabled;
    private int limit;
    private List<String> messages;

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        configuration.set("chunk.enabled", this.enabled);
        plugin.saveConfig();
    }

    public final int getLimit() {
        return limit;
    }

    public final void setLimit(final int limit) {
        this.limit = limit;
        configuration.set("chunk.limit", this.limit);
        plugin.saveConfig();
    }

    public final List<String> getMessages() {
        return messages;
    }
}

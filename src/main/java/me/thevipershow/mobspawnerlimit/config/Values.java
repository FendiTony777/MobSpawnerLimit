package me.thevipershow.mobspawnerlimit.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Values {

    private static boolean enabled;
    private static int limit;
    private static List<String> messages;

    public void setValues(FileConfiguration configuration) {
        enabled = configuration.getBoolean("chunk.enabled");
        limit = configuration.getInt("chunk.limit");
        messages = configuration.getStringList("chunk.messages");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled, FileConfiguration configuration) {
        Values.enabled = enabled;
        configuration.set("chunk.enabled", enabled);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit, FileConfiguration configuration) {
        Values.limit = limit;
        configuration.set("chunk.limit", limit);
    }

    public List<String> getMessages() {
        return messages;
    }
}

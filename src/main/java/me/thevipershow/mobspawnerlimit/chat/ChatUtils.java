package me.thevipershow.mobspawnerlimit.chat;

import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.config.ConfigContext;
import me.thevipershow.mobspawnerlimit.config.ConfigNode;
import me.thevipershow.mobspawnerlimit.config.TomlConfiguration;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public final class ChatUtils {

    private static final String PREFIX = color("&7[&aMobSpawnerLimit&7]: ");

    private ChatUtils() {
        throw new UnsupportedOperationException("You cannot instantiate this class as it's a utility class.");
    }

    @NotNull
    public static String color(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @NotNull
    public static String replaceAllPlaceholdersInString(@NotNull String string) {
        TomlConfiguration mainConfig = MobSpawnerLimit.INSTANCE.getLoadedTomlConfigs().get(ConfigContext.MAIN);
        int xDist = mainConfig.getValue(ConfigNode.CHECKS_R_X_DIST);
        int yDist = mainConfig.getValue(ConfigNode.CHECKS_R_Y_DIST);
        int zDist = mainConfig.getValue(ConfigNode.CHECKS_R_Z_DIST);

        string = Placeholder.PREFIX.replaceInString(string, PREFIX);
        string = Placeholder.MIN_X.replaceInString(string, String.valueOf(xDist));
        string = Placeholder.MIN_Y.replaceInString(string, String.valueOf(yDist));
        string = Placeholder.MIN_Z.replaceInString(string, String.valueOf(zDist));
        return string;
    }
}

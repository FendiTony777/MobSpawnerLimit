package me.thevipershow.mobspawnerlimit.enums;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum Messages {

    NO_PERMISSION(" &cYou are missing permission &f`&7%s&f."),
    WRONG_ARGS(" &cInvalid argument&8 -> &f`&7%s&f'."),
    WRONG_NUMBER(" &cInvalid arguments number; &cexpected &8[&7%s&8]."),
    TOO_MANY_ARGS(" &cYou have used too many arguments!"),
    INVALID_RANGE(" &cProvided number is outside of range &7(&f1≤n≤65536&7)"),
    UPDATE_FAILED(" &cValue &7%s &cwas invalid."),
    SPAWNER_LIMIT(" &cThis chunks has too many spawners &7(&cMAX: &f%d&7)&r"),
    UPDATED_CORRECTLY(" &7Values inside the &6config.yml &7have been correctly updated."),
    PREFIX("&8[&6MobSpawnerLimit&8]&7:");

    private final String string;

    Messages(String string) {
        this.string = string;
    }

    public static String color(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendMessage(final CommandSender sender, final String s) {
        sender.sendMessage(color(PREFIX.string + s));
    }

    public static void sendMessage(final CommandSender sender, final Messages messages) {
        sender.sendMessage(color(PREFIX.string + messages.string));
    }

    public final String getString() {
        return string;
    }

    public final String getString(final Object o) {
        return color(String.format(string, o));
    }
}

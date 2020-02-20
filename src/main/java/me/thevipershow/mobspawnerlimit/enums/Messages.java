package me.thevipershow.mobspawnerlimit.enums;

import me.thevipershow.mobspawnerlimit.utils.Utils;

public enum Messages {

    NO_PERMISSION("&4» &cYou are missing permission &f'&7%s&f'"),
    WRONG_ARGS("&4» &cInvalid arguments&8 -> &f'&7%s&f'"),
    WRONG_NUMBER("&4» &cInvalid arguments number; &cexpected &8[&7%s&8]"),
    UPDATE_SUCCESS("&2» &aValue updated successfully to &7%s"),
    UPDATE_FAILED("&4» &cValue &7%s &cwas invalid");

    private String string;

    Messages(String string) {
        this.string = string;
    }

    public String getString(String formatter) {
        return Utils.color(string.replace("%s", formatter));
    }
}

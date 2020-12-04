package me.thevipershow.mobspawnerlimit.chat;

import org.jetbrains.annotations.NotNull;

public enum Placeholder {

    PREFIX("{PREFIX}"),
    MIN_X("{MIN_X}"),
    MIN_Y("{MIN_Y}"),
    MIN_Z("{MIN_Z}");

    Placeholder(@NotNull String matcher) {
        this.matcher = matcher;
    }

    private final String matcher;

    @NotNull
    public final String getMatcher() {
        return matcher;
    }

    @NotNull
    public final String replaceInString(@NotNull String string, @NotNull String with) {
        return with.replace(this.matcher, with);
    }
}

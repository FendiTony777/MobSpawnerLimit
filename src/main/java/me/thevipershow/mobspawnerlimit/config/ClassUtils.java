package me.thevipershow.mobspawnerlimit.config;

import org.jetbrains.annotations.NotNull;

public final class ClassUtils {

    private ClassUtils() {
        throw new UnsupportedOperationException("You cannot instantiate a utility class.");
    }

    @NotNull
    public static <T extends Class<?>> T checkForEmptyConstructor(@NotNull T anyClass) {
        try {
            anyClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format("The class %s did not have an empty constructor!", anyClass.getSimpleName()));
        }
        return anyClass;
    }

    @NotNull
    public static <T> T buildWithEmptyConstructor(Class<T> type) {
        try {
            return type.getConstructor().newInstance();
        } catch (Exception ignored) {
        }
        return null; // impossible.
    }
}

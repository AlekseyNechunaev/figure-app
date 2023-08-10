package ru.fc2.figure.utils;

public final class StringUtils {

    public static boolean checkNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private StringUtils() {

    }

}

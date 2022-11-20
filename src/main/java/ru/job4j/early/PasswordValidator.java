package ru.job4j.early;

import java.util.Locale;

public class PasswordValidator {
    private static final String[] INVALID_STRINGS = {
            "qwerty", "12345", "password", "admin", "user"};

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        var length = password.length();
        if (length < 8 || length > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;
        char[] array = password.toCharArray();
        for (char symbol : array) {
            if (Character.isUpperCase(symbol)) {
                upper = true;
            }
            if (Character.isLowerCase(symbol)) {
                lower = true;
            }
            if (Character.isDigit(symbol)) {
                digit = true;
            }
            if (!Character.isLetter(symbol) && !Character.isDigit(symbol)) {
                special = true;
            }
            if (upper && lower && digit && special) {
                break;
            }
        }
        if (!upper) {
            throw new IllegalArgumentException(
                    "Password should contain at least one uppercase letter");
        }
        if (!lower) {
            throw new IllegalArgumentException(
                    "Password should contain at least one lowercase letter");
        }
        if (!digit) {
            throw new IllegalArgumentException(
                    "Password should contain at least one figure");
        }
        if (!special) {
            throw new IllegalArgumentException(
                    "Password should contain at least one special symbol");
        }
        String register = password.toLowerCase(Locale.ROOT);
        for (String string : INVALID_STRINGS) {
            if (register.contains(string)) {
                throw new IllegalArgumentException(
                        "Password shouldn't contain substrings:"
                               + " qwerty, 12345, password, admin, user");
            }
        }
        return password;
    }
}

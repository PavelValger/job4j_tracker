package ru.job4j.early;

import java.util.Locale;
import java.util.function.Predicate;

public class PasswordValidator {
    private static char[] array;

    private static void isValidate(Predicate<Character> predicate, String exception) {
        boolean valid = false;
        for (char symbol : array) {
            if (predicate.test(symbol)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException(exception);
        }
    }

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        var length = password.length();
        if (length < 8 || length > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        array = password.toCharArray();
        Predicate<Character> isUpperCase = Character::isUpperCase;
        isValidate(isUpperCase, "Password should contain at least one uppercase letter");
        Predicate<Character> isLowerCase = Character::isLowerCase;
        isValidate(isLowerCase, "Password should contain at least one lowercase letter");
        Predicate<Character> isDigit = Character::isDigit;
        isValidate(isDigit, "Password should contain at least one figure");
        Predicate<Character> isSpecial = symbol -> !Character.isLetter(symbol)
                && !Character.isDigit(symbol);
        isValidate(isSpecial, "Password should contain at least one special symbol");
        String register = password.toLowerCase(Locale.ROOT);
        if (password.contains("12345") || register.contains("qwerty")
                || register.contains("password") || register.contains("admin")
                || register.contains("user")) {
            throw new IllegalArgumentException(
                    "Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }
}

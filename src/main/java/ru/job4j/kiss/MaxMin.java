package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    private <T> void validate(List<T> value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(
                    "The transmitted list does not contain any elements");
        }
    }

    private <T> T calculation(List<T> value, BiPredicate<T, T> predicate) {
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T second = value.get(i);
            if (predicate.test(rsl, second)) {
                rsl = second;
            }
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        validate(value);
        return calculation(value, (rsl, second) -> comparator.compare(rsl, second) < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        validate(value);
        return calculation(value, (rsl, second) -> comparator.compare(rsl, second) > 0);
    }
}
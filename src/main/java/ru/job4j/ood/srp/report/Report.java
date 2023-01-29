package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

public interface Report<T> {
    String generate(Predicate<T> filter);
}

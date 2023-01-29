package ru.job4j.ood.srp.store;

import java.util.List;
import java.util.function.Predicate;

public interface Store<T> {
    void add(T obj);

    List<T> findBy(Predicate<T> filter);
}

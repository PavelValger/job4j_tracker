package ru.job4j.ood.srp.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore<T> implements Store<T> {
    private final List<T> list = new ArrayList<>();

    @Override
    public void add(T obj) {
        list.add(obj);
    }

    @Override
    public List<T> findBy(Predicate<T> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }
}

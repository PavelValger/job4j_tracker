package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.LinkedList;
import java.util.List;

public class TemporaryStore implements Store {
    private final List<Food> temporaryStorage = new LinkedList<>();

    @Override
    public boolean add(Food food) {
        return temporaryStorage.add(food);
    }

    @Override
    public List<Food> getAll() {
        return new LinkedList<>(temporaryStorage);
    }

    @Override
    public void clear() {
        temporaryStorage.clear();
    }
}

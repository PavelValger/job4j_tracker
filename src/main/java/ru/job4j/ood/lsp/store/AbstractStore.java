package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> list = new ArrayList<>();

    @Override
    public abstract void add(Food food, float foodQuality);

    @Override
    public abstract int getMinQuality();

    @Override
    public abstract int getMaxQuality();

    public List<Food> getList() {
        return list;
    }
}

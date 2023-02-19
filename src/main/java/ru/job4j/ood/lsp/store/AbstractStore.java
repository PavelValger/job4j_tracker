package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> list = new ArrayList<>();

    protected abstract boolean isSuitable(Food food);

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (isSuitable(food)) {
            list.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void clear() {
        list.clear();
    }
}

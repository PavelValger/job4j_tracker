package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Trash extends AbstractStore {

    @Override
    public void add(Food food, float foodQuality) {
        super.getList().add(food);
    }

    @Override
    public int getMinQuality() {
        return 100;
    }

    @Override
    public int getMaxQuality() {
        return 100000;
    }
}

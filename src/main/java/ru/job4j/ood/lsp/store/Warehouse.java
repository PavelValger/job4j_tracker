package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Warehouse extends AbstractStore {

    @Override
    public void add(Food food, float foodQuality) {
        super.getList().add(food);
    }

    @Override
    public int getMinQuality() {
        return 0;
    }

    @Override
    public int getMaxQuality() {
        return 25;
    }
}

package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {
    private static final int CRITICAL_QUALITY = 75;

    @Override
    public void add(Food food, float foodQuality) {
        if (foodQuality > CRITICAL_QUALITY) {
            food.setPrice(food.getPrice() * food.getDiscount());
        }
        super.getList().add(food);
    }

    @Override
    public int getMinQuality() {
        return 25;
    }

    @Override
    public int getMaxQuality() {
        return 100;
    }
}

package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        float foodQuality = foodsQuality(food);
        if (foodQuality > 25 && foodQuality <= 100) {
            if (foodQuality > 75) {
                food.setPrice(food.getPrice() * food.getDiscount());
            }
            rsl = super.getAll().add(food);
        }
        return rsl;
    }
}

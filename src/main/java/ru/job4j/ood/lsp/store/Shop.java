package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {
    private final ExpirationCalculator expirationCalculator;

    public Shop(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        float foodQuality = expirationCalculator.foodsQuality(food);
        if (foodQuality > 75) {
            food.setPrice(food.getPrice() * food.getDiscount());
        }
        return foodQuality > 25 && foodQuality <= 100;
    }
}

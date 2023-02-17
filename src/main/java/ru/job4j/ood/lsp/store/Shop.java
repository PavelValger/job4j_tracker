package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {
    private static final int MIN_QUALITY = 25;
    private static final int MAX_QUALITY = 100;
    private static final int DISCOUNT_QUALITY = 75;
    private final ExpirationCalculator expirationCalculator;

    public Shop(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        boolean rsl = false;
        float foodQuality = expirationCalculator.foodsQuality(food);
        if (foodQuality > MIN_QUALITY && foodQuality <= MAX_QUALITY) {
            rsl = true;
            if (foodQuality > DISCOUNT_QUALITY) {
                food.setPrice(food.getPrice() * food.getDiscount());
            }
        }
        return rsl;
    }
}

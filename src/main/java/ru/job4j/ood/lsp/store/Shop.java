package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {
    private static final int MIN_QUALITY = 25;
    private static final int MAX_QUALITY = 100;
    private static final int DISCOUNT_QUALITY = 75;
    private final ExpirationCalculator expirationCalculator;
    private float foodQuality;

    public Shop(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        foodQuality = expirationCalculator.foodsQuality(food);
        return foodQuality > MIN_QUALITY && foodQuality <= MAX_QUALITY;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (isSuitable(food)) {
            if (foodQuality > DISCOUNT_QUALITY) {
                food.setPrice(food.getPrice() * food.getDiscount());
            }
            getList().add(food);
            rsl = true;
        }
        return rsl;
    }
}

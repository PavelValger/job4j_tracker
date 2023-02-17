package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.model.Food;

public class Warehouse extends AbstractStore {
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 25;
    private final ExpirationCalculator expirationCalculator;

    public Warehouse(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        float foodQuality = expirationCalculator.foodsQuality(food);
        return foodQuality > MIN_QUALITY && foodQuality <= MAX_QUALITY;
    }
}

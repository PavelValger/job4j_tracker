package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.model.Food;

public class Trash extends AbstractStore {
    private static final int MIN_QUALITY = 100;
    private static final int MAX_QUALITY = 1000000;
    private final ExpirationCalculator expirationCalculator;

    public Trash(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        float foodQuality = expirationCalculator.foodsQuality(food);
        return foodQuality > MIN_QUALITY && foodQuality <= MAX_QUALITY;
    }
}

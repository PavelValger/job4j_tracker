package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.model.Food;

public class Trash extends AbstractStore {
    private final ExpirationCalculator expirationCalculator;

    public Trash(ExpirationCalculator expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isSuitable(Food food) {
        float foodQuality = expirationCalculator.foodsQuality(food);
        return foodQuality > 100 && foodQuality <= 1000000;
    }
}

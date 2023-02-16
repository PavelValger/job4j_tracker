package ru.job4j.ood.lsp.calculator;

import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class FoodExpirationCalculator implements ExpirationCalculator {

    @Override
    public float foodsQuality(Food food) {
        LocalDateTime createDate = food.getCreateDate();
        LocalDateTime expiryDate = food.getExpiryDate();
        LocalDateTime now = LocalDateTime.now();
        float shelfLife = (float) HOURS.between(createDate, expiryDate);
        float storageTime = (float) HOURS.between(createDate, now);
        if (shelfLife < 0 || storageTime < 0) {
            throw new IllegalArgumentException("Date parameters entered incorrectly");
        }
        return (storageTime / shelfLife) * 100;
    }
}

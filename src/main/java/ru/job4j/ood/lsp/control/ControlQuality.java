package ru.job4j.ood.lsp.control;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Store;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> storeList) {
        this.stores = storeList;
    }

    private float foodsQuality(Food food) {
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

    public void distribution(Food food) {
        float foodQuality = foodsQuality(food);
        for (Store store : stores) {
            if (foodQuality > store.getMinQuality() && foodQuality <= store.getMaxQuality()) {
                store.add(food, foodQuality);
            }
        }
    }
}

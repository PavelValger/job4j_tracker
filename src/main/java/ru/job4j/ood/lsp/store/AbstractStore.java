package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

public abstract class AbstractStore implements Store {
    private final List<Food> list = new ArrayList<>();
    private int minQuality;
    private int maxQuality;

    public AbstractStore() {
        setMinQuality(minQuality);
        setMaxQuality(maxQuality);
    }

    protected float foodsQuality(Food food) {
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

    protected void setMinQuality(int minQuality) {
        this.minQuality = minQuality;
    }

    protected void setMaxQuality(int maxQuality) {
        this.maxQuality = maxQuality;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        float foodQuality = foodsQuality(food);
        if (foodQuality > minQuality && foodQuality <= maxQuality) {
            rsl = getAll().add(food);
        }
        return rsl;
    }

    @Override
    public List<Food> getAll() {
        return list;
    }

    @Override
    public void clear() {
        list.clear();
    }
}

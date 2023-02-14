package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public interface Store {
    void add(Food food, float foodQuality);

    int getMinQuality();

    int getMaxQuality();
}

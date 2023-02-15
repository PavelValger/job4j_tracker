package ru.job4j.ood.lsp.control;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Store;

import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> storeList) {
        this.stores = storeList;
    }

    public void distribution(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }
}

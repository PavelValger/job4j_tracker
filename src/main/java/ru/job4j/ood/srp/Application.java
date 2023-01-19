package ru.job4j.ood.srp;

import ru.job4j.tracker.Item;

import java.util.List;

public interface Application {
    Item add(Item item);

    void findAll(List<Item> items);
}

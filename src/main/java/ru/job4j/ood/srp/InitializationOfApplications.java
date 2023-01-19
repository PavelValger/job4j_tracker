package ru.job4j.ood.srp;

import ru.job4j.tracker.Item;

import java.util.List;

public class InitializationOfApplications implements Application {
    @Override
    public Item add(Item item) {
        return null;
    }

    @Override
    public void findAll(List<Item> items) {
        System.out.println(items);
    }
}

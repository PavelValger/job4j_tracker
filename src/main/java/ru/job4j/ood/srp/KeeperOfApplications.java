package ru.job4j.ood.srp;

import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;

public class KeeperOfApplications {
    public void save(Item item) {
        List<Item> list = new ArrayList<>();
        list.add(item);
    }
}

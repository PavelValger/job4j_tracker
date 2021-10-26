package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] array = new Item[items.length];
        int size = 0;
        for (Item all : items) {
            if (all != null) {
                array[size] = all;
                size++;
            }
        }
        return Arrays.copyOf(array, size);
    }

    public Item[] findByName(String key) {
        Item[] array = new Item[items.length];
        int size = 0;
        for (Item name : items) {
            if (name != null && (key.equals(name.getName()))) {
                array[size] = name;
                size++;
            }
        }
        return Arrays.copyOf(array, size);
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item receive = items[index];
            if (receive.getId() == id) {
                rsl = receive;
                break;
            }
        }
        return rsl;
    }
}
package ru.job4j.tracker;

import java.util.List;

public record DeleteAllAction(Output out) implements UserAction {

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete all items ===");
        List<Item> clear = tracker.findAll();
        int count = 1;
        while (count <= clear.size()) {
            tracker.delete(count);
            count++;
        }
        System.gc();
        out.println("Хранилище очищено");
        return true;
    }
}

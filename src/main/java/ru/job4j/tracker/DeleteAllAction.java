package ru.job4j.tracker;

import java.util.List;

public class DeleteAllAction implements UserAction {
    private final Output out;

    public DeleteAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete all items ===");
        List<Item> clear = tracker.findAll();
        for (Item item : clear) {
            tracker.delete(item.getId());
        }
        System.gc();
        out.println("Хранилище очищено");
        return true;
    }
}

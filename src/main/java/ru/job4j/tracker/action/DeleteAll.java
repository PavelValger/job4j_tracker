package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.store.Store;
import ru.job4j.tracker.input.Input;

import java.util.List;

public class DeleteAll implements UserAction {
    private final Output out;

    public DeleteAll(Output out) {
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

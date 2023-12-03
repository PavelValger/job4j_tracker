package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.store.Store;
import ru.job4j.tracker.input.Input;

public class CreateMuch implements UserAction {
    private final Output out;

    public CreateMuch(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new much Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new much Item ====");
        int count = Integer.parseInt(input.askStr("Enter count: "));
        for (int i = 1; i <= count; i++) {
            String name = String.valueOf(i);
            Item item = new Item(name);
            tracker.add(item);
        }
        out.println("Заявки добавлены");
        return true;
    }
}

package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.store.Store;
import ru.job4j.tracker.input.Input;

import java.util.Objects;

public class FindId implements UserAction {
    private final Output out;

    public FindId(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find item by id ===");
        int id = input.askInt("Enter id: ");
        Item item = tracker.findById(id);
        out.println(Objects.requireNonNullElseGet(
                item, () -> "Заявка с введенным id: " + id + " не найдена"));
        return true;
    }
}

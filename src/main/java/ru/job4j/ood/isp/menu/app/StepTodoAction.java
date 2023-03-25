package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

import java.util.NoSuchElementException;

public class StepTodoAction implements TodoAction {
    private static final String SEPARATOR = System.lineSeparator();
    private final Output out;

    public StepTodoAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Call an action linked to a menu point";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.print(String.format("%s%s", "=== Call an action ====", SEPARATOR));
        String point = input.askStr("Enter point name: ");
        try {
            menu.select(point).orElseThrow().getActionDelegate().delegate();
        } catch (NoSuchElementException see) {
            out.print(String.format("%s%s", "Point not found", SEPARATOR));
        }
        return true;
    }
}

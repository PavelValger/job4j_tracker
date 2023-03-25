package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

public class ExitTodoAction implements TodoAction {
    private final Output out;

    public ExitTodoAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.print("=== Exit Program ===");
        return false;
    }
}

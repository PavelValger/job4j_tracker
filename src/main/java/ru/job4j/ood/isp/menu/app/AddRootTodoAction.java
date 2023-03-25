package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

public class AddRootTodoAction implements TodoAction {
    private static final String SEPARATOR = System.lineSeparator();
    private final Output out;
    private final ActionDelegate actionDelegate;

    public AddRootTodoAction(Output out, ActionDelegate actionDelegate) {
        this.out = out;
        this.actionDelegate = actionDelegate;
    }

    @Override
    public String name() {
        return "Add root point";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.print("=== Add a new root point ====" + SEPARATOR);
        String parentName = input.askStr("Enter name: ");
        return menu.add(null, parentName, actionDelegate);
    }
}

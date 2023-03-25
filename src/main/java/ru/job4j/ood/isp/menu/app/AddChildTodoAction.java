package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.ActionDelegate;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

public class AddChildTodoAction implements TodoAction {
    private static final String SEPARATOR = System.lineSeparator();
    private final Output out;
    private final ActionDelegate actionDelegate;

    public AddChildTodoAction(Output out, ActionDelegate actionDelegate) {
        this.out = out;
        this.actionDelegate = actionDelegate;
    }

    @Override
    public String name() {
        return "Add child point";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.print("=== Add a new child point ====" + SEPARATOR);
        String parentName = input.askStr("Enter parent name: ");
        while (menu.select(parentName).isEmpty()) {
            parentName = input.askStr("Please enter validate root or null: ");
            if (parentName.equals("null")) {
                out.print("Will be added a new root point" + SEPARATOR);
                break;
            }
        }
        String childName = input.askStr("Enter child name: ");
        return menu.add(parentName, childName, actionDelegate);
    }
}

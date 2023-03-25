package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.Output;

public class OutputTodoAction implements TodoAction {
    private static final String SEPARATOR = System.lineSeparator();
    private final Output out;
    private final MenuPrinter menuPrinter;

    public OutputTodoAction(Output out, MenuPrinter menuPrinter) {
        this.out = out;
        this.menuPrinter = menuPrinter;
    }

    @Override
    public String name() {
        return "Show menu";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.print(String.format("%s%s", "=== Show menu ====", SEPARATOR));
        menuPrinter.print(menu);
        return true;
    }
}

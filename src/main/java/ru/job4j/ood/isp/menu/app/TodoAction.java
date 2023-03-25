package ru.job4j.ood.isp.menu.app;

import ru.job4j.ood.isp.menu.Menu;

public interface TodoAction {
    String name();

    boolean execute(Input input, Menu menu);
}

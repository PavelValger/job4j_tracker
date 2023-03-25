package ru.job4j.ood.isp.menu;

import static java.lang.System.out;

public class ConsoleOutput implements Output {

    @Override
    public void print(Object obj) {
        out.print(obj);
    }
}

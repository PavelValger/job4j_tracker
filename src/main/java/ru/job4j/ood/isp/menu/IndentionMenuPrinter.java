package ru.job4j.ood.isp.menu;

public class IndentionMenuPrinter implements MenuPrinter {
    private static final String INDENTION = "-";
    private static final String SEPARATOR = System.lineSeparator();
    private final Output out;

    public IndentionMenuPrinter(Output out) {
        this.out = out;
    }

    @Override
    public void print(Menu menu) {
        menu.iterator().forEachRemaining(menuItemInfo -> out.print(String.format("%s%s%s%s",
                INDENTION.repeat(Math.max(0, menuItemInfo.getNumber().length() - 2)),
                menuItemInfo.getNumber(),
                menuItemInfo.getName(),
                SEPARATOR)));
    }
}

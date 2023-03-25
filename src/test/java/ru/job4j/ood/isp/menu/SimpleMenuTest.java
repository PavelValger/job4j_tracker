package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String SP = System.lineSeparator();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").orElseThrow());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").orElseThrow());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").orElseThrow());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectThenResult() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить одежду", STUB_ACTION);
        menu.add("Купить одежду", "Купить кроссовки", STUB_ACTION);
        assertThat(menu.select("Купить одежду").orElseThrow())
                .isEqualTo(new Menu.MenuItemInfo(
                        "Купить одежду", List.of("Купить кроссовки"), STUB_ACTION, "1.2."));
    }

    @Test
    public void whenSelectAndMenuIsEmptyThenEmpty() {
        Menu menu = new SimpleMenu();
        String rsl = null;
        if (menu.select("qwerty").isEmpty()) {
            rsl = "";
        }
        assertThat(rsl).isEmpty();
    }

    @Test
    public void whenSelectStringNullThenEmpty() {
        Menu menu = new SimpleMenu();
        String rsl = null;
        if (menu.select(null).isEmpty()) {
            rsl = "";
        }
        assertThat(rsl).isEmpty();
    }

    @Test
    public void whenMenuPrinter() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить животное", STUB_ACTION);
        menu.add("Решить задачу на job4j", "Решить задачу на job4j", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить одежду", STUB_ACTION);
        menu.add("Купить одежду", "Купить кроссовки", STUB_ACTION);
        menu.add("Покормить животное", "Покормить кота", STUB_ACTION);
        new IndentionMenuPrinter(new ConsoleOutput()).print(menu);
        StubOutput out = new StubOutput();
        new IndentionMenuPrinter(out).print(menu);
        String rsl =
                "1.Сходить в магазин" + SP
                + "--1.1.Купить продукты" + SP
                + "----1.1.1.Купить хлеб" + SP
                + "----1.1.2.Купить молоко" + SP
                + "--1.2.Купить одежду" + SP
                + "----1.2.1.Купить кроссовки" + SP
                + "2.Покормить животное" + SP
                + "--2.1.Покормить кота" + SP
                + "3.Решить задачу на job4j" + SP;
        assertThat(out.toString()).isEqualTo(rsl);
    }
}
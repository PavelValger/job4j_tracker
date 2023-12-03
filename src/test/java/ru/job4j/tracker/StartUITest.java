package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.Stub;
import ru.job4j.tracker.store.MemTracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void createItem() {
        Output out = new Stub();
        Input input = new ru.job4j.tracker.input.Stub(new String[]{"0", "test", "1"});
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Create(out));
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("test"));
    }

    @Test
    public void editItem() {
        Output out = new Stub();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        Input input = new ru.job4j.tracker.input.Stub(new String[]{"0",
                String.valueOf(item.getId()), "test100", "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Edit(out));
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is("test100"));
    }

    @Test
    public void deleteItem() {
        Output out = new Stub();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        Input in = new ru.job4j.tracker.input
                .Stub(new String[] {"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Delete(out));
        actions.add(new Exit(out));
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void replaceEditItem() {
        Output out = new Stub();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        Input input = new ru.job4j.tracker.input.Stub(new String[] {"0",
                String.valueOf(item.getId()), "test100", "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Edit(out));
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно" + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln));
    }

    @Test
    public void replaceShowItem() {
        Output out = new Stub();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        Input input = new ru.job4j.tracker.input.Stub(new String[] {"0", "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Show(out));
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln));
    }

    @Test
    public void replaceFindIdItem() {
        Output out = new Stub();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        Input input = new ru.job4j.tracker.input
                .Stub(new String[] {"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindId(out));
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln));
    }

    @Test
    public void replaceFindNameItem() {
        Output out = new Stub();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        Input input = new ru.job4j.tracker.input.Stub(new String[] {"0", "test", "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindName(out));
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + item.toString() + ln
                        + "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new Stub();
        Input input = new ru.job4j.tracker.input.Stub(new String[] {"1", "0"});
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit(out));
        new StartUI(out).init(input, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu:" + ln
                                + "0. Exit Program" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu:" + ln
                                + "0. Exit Program" + ln
                                + "=== Exit Program ===" + ln));
    }
}

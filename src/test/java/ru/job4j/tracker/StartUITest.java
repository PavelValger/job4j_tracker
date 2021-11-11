package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void createItem() {
        Output out = new StubOutput();
        Input input = new StubInput(new String[]{"0", "test", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(out), new ExitAction(out)};
        new StartUI(out).init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("test"));
    }

    @Test
    public void editItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[]{"0",
                String.valueOf(item.getId()), "test100", "1"});
        UserAction[] actions = {new EditAction(out), new ExitAction(out)};
        new StartUI(out).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is("test100"));
    }

    @Test
    public void deleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {new DeleteAction(out), new ExitAction(out)};
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void replaceEditItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[] {"0",
                String.valueOf(item.getId()), "test100", "1"});
        UserAction[] actions = {new EditAction(out), new ExitAction(out)};
        new StartUI(out).init(input, tracker, actions);
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
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[] {"0", "1"});
        UserAction[] actions = {new ShowAction(out), new ExitAction(out)};
        new StartUI(out).init(input, tracker, actions);
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
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {new FindIdAction(out), new ExitAction(out)};
        new StartUI(out).init(input, tracker, actions);
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
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[] {"0", "test", "1"});
        UserAction[] actions = {new FindNameAction(out), new ExitAction(out)};
        new StartUI(out).init(input, tracker, actions);
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
}
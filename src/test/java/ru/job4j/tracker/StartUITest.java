package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void createItem() {
        Input input = new StubInput(new String[]{"0", "test", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(new StubOutput()),
                new ExitAction(new StubOutput())};
        new StartUI(new StubOutput()).init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("test"));
    }

    @Test
    public void editItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[]{"0",
                String.valueOf(item.getId()), "test100", "1"});
        UserAction[] actions = {new EditAction(new StubOutput()),
                new ExitAction(new StubOutput())};
        new StartUI(new StubOutput()).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is("test100"));
    }

    @Test
    public void deleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {new DeleteAction(new StubOutput()),
                new ExitAction(new StubOutput())};
        new StartUI(new StubOutput()).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new StubInput(new String[] {"0",
                String.valueOf(item.getId()), replaceName, "1"});
        UserAction[] actions = new UserAction[]{
                new EditAction(out), new ExitAction(out)};
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
}
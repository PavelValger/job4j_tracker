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
        UserAction[] actions = {new CreateAction(new ConsoleOutput()), new ExitAction()};
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("test"));
    }

    @Test
    public void editItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input input = new StubInput(new String[]{"0",
                String.valueOf(item.getId()), "test100", "1"});
        UserAction[] actions = {new EditAction(), new ExitAction()};
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is("test100"));
    }

    @Test
    public void deleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {new DeleteAction(), new ExitAction()};
        new StartUI(new ConsoleOutput()).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}
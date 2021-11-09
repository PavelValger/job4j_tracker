package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void createItem() {
        Input input = new StubInput(new String[]{"0", "Item name", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(), new ExitAction()};
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }
}
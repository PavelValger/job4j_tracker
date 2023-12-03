package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.Validate;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.Stub;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new Stub();
        Input in = new ru.job4j.tracker.input.Stub(new String[] {"one", "1"});
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void withCorrectInput() {
        Output out = new Stub();
        Input in = new ru.job4j.tracker.input.Stub(new String[] {"1"});
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void withMultipleCorrectInput() {
        Output out = new Stub();
        Input in = new ru.job4j.tracker.input.Stub(new String[] {"1", "2", "5"});
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
        selected = input.askInt("Enter menu:");
        assertThat(selected, is(2));
        selected = input.askInt("Enter menu:");
        assertThat(selected, is(5));
    }

    @Test
    public void enteringNegativeNumber() {
        Output out = new Stub();
        Input in = new ru.job4j.tracker.input.Stub(new String[] {"-3"});
        Validate input = new Validate(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-3));
    }
}
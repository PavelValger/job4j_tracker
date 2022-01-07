package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemAscByNameTest {

    @Test
    public void directComparison() {
        List<Item> items = Arrays.asList(
                new Item("Qwerty"),
                new Item("Denis"),
                new Item("Amadey"),
                new Item("Felix"));
        List<Item> expected = Arrays.asList(
                new Item("Amadey"),
                new Item("Denis"),
                new Item("Felix"),
                new Item("Qwerty"));
        items.sort(new ItemAscByName());
        assertThat(items, is(expected));
    }
}
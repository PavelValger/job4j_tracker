package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemDescByNameTest {

    @Test
    public void reverseComparison() {
        List<Item> items = Arrays.asList(
                new Item("Amadey"),
                new Item("Qwerty"),
                new Item("Felix"),
                new Item("Denis"));
        List<Item> expected = Arrays.asList(
                new Item("Qwerty"),
                new Item("Felix"),
                new Item("Denis"),
                new Item("Amadey"));
        items.sort(new ItemDescByName());
        assertThat(items, is(expected));
    }
}
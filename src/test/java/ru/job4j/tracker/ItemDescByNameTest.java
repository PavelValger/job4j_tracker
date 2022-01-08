package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemDescByNameTest {

    @Test
    public void reverseComparison() {
        Item first = new Item("Amadey");
        Item second = new Item("Denis");
        Item third = new Item("Felix");
        Item fourth = new Item("Qwerty");
        List<Item> items = Arrays.asList(first, fourth, third, second);
        List<Item> expected = List.of(fourth, third, second, first);
        items.sort(new ItemDescByName());
        assertThat(items, is(expected));
    }
}
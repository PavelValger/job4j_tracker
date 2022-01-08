package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemAscByNameTest {

    @Test
    public void directComparison() {
        Item first = new Item("Amadey");
        Item second = new Item("Denis");
        Item third = new Item("Felix");
        Item fourth = new Item("Qwerty");
        List<Item> items = Arrays.asList(fourth, second, third, first);
        List<Item> expected = List.of(first, second, third, fourth);
        items.sort(new ItemAscByName());
        assertThat(items, is(expected));
    }
}
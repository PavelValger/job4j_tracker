package ru.job4j.kiss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MaxMinTest {
    private final List<Integer> list = List.of(1, 5, 3, 8, 4, 10, 7);
    private final MaxMin maxMin = new MaxMin();

    @Test
    void checkMaximumValue() {
        Comparator<Integer> comparator = Integer::compare;
        Assertions.assertEquals(10, maxMin.max(list, comparator));
    }

    @Test
    void checkMinimumValue() {
        Comparator<Integer> comparator = Integer::compare;
        Assertions.assertEquals(1, maxMin.min(list, comparator));
    }

    @Test
    void whenMinAndListIsEmptyThenException() {
        List<String> stringList = new ArrayList<>();
        Comparator<String> comparator = String::compareTo;
        assertThatThrownBy(() -> maxMin.min(stringList, comparator))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void whenMaxAndListIsEmptyThenException() {
        List<String> stringList = new ArrayList<>();
        Comparator<String> comparator = String::compareTo;
        assertThatThrownBy(() -> maxMin.max(stringList, comparator))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }
}
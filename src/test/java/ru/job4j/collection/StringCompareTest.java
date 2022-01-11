package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StringCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void registerAndLength() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "PAVEL",
                "PAVe"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void register() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Felix",
                "aAron"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void lengthStringPositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "automobile",
                "auto"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void emptiness() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                " ",
                "auto"
        );
        assertThat(rst, lessThan(0));
    }
}
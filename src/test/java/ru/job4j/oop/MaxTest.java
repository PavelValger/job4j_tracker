package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class MaxTest {

    @Test
    public void max2() {
        int first = 1;
        int second = 2;
        int result = Max.max(first, second);
        int expected = 2;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void max3() {
        int first = 10;
        int second = 15;
        int third = 7;
        int result = Max.max(first, second, third);
        int expected = 15;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void max4() {
        int first = 10;
        int second = 15;
        int third = 40;
        int fourth = -55;
        int result = Max.max(first, second, third, fourth);
        int expected = 40;
        Assert.assertEquals(result, expected);
    }
}
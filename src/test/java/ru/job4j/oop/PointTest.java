package ru.job4j.oop;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void distance2() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double dist = a.distance(b);
        assertThat(dist, closeTo(2, 0.001));
    }

    @Test
    public void distance4() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 4);
        double dist = a.distance(b);
        assertThat(dist, closeTo(4.47, 0.01));
    }
}
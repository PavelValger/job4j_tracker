package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FunctRangeTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = FunctRange.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void linear() {
        List<Double> result = FunctRange.diapason(2, 4, x -> x);
        List<Double> expected = Arrays.asList(2D, 3D);
        assertThat(result, is(expected));
    }

    @Test
    public void quadratic() {
        List<Double> result = FunctRange.diapason(2, 4, x -> Math.pow(x, 2));
        List<Double> expected = Arrays.asList(4D, 9D);
        assertThat(result, is(expected));
    }

    @Test
    public void indicative() {
        List<Double> result = FunctRange.diapason(0, 4, x -> Math.pow(3, x));
        List<Double> expected = Arrays.asList(1D, 3D, 9D, 27D);
        assertThat(result, is(expected));
    }
}
package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderConvertTest {

    @Test
    public void process() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
    }

    @Test
    public void duplicate() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3qwerty", "Clone"));
        orders.add(new Order("3qwerty", "Clo"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3qwerty"), is(new Order("3qwerty", "Clone")));
    }

    @Test
    public void duplicateFalse() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3qwerty", "Clone"));
        orders.add(new Order("3qwerty", "Clo"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertFalse(map.containsValue(new Order("3qwerty", "Clo")));
    }
}
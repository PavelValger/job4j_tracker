package ru.job4j.ood.ocp;

import java.util.List;

public class Collector {
    private final Basket<Apple> basket;

    public Collector(Basket<Apple> basket) {
        this.basket = basket;
    }

    public Apple add(Apple fruit) {
        Apple rsl = null;
        basket.pack();
        if (basket.getList().isPresent()) {
            List<Apple> list = basket.getList().get();
            list.add(new Apple());
            rsl = list.get(0);
        }
        return rsl;
    }
}

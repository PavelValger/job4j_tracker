package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Basket<Apple> implements Packing {
    private List<Apple> list = null;

    @Override
    public void pack() {
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public Optional<List<Apple>> getList() {
        return Optional.ofNullable(list);
    }
}

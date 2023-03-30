package ru.job4j.ood.dip;

import ru.job4j.ood.lsp.control.ControlQuality;
import ru.job4j.ood.lsp.model.Cheese;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    private ControlQuality controlQuality;
    private final List<Cheese> list = new LinkedList<>();

    @Override
    public boolean add(Cheese food) {
        System.out.println("Результат");
        return false;
    }

    @Override
    public List<Cheese> getAll() {
        return null;
    }
}

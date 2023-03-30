package ru.job4j.ood.dip;

import ru.job4j.ood.lsp.model.Cheese;

import java.util.List;

public interface Storage {
    boolean add(Cheese food);

    List<Cheese> getAll();
}

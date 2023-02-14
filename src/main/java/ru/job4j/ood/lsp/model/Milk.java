package ru.job4j.ood.lsp.model;

import java.time.LocalDateTime;

public class Milk extends Food {
    public Milk(String name, LocalDateTime createDate,
                LocalDateTime expiryDate, float price, float discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}

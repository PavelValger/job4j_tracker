package ru.job4j.ood.lsp.model;

import java.time.LocalDateTime;

public class Cheese extends Food {
    public Cheese(String name, LocalDateTime createDate,
                  LocalDateTime expiryDate, float price, float discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}

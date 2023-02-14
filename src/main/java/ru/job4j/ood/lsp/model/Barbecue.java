package ru.job4j.ood.lsp.model;

import java.time.LocalDateTime;

public class Barbecue extends Food {
    public Barbecue(String name, LocalDateTime createDate,
                    LocalDateTime expiryDate, float price, float discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}

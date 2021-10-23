package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Item item1 = new Item(1, "Дата и время");
        LocalDateTime event = item1.getCreated();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String view = event.format(formatter);
        System.out.println("Текущие дата и время: " + view);
    }
}

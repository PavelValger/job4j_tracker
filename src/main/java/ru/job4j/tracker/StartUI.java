package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Item date = new Item(1, "Дата и время");
        LocalDateTime event = date.getCreated();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String view = event.format(formatter);
        System.out.println("Текущие дата и время: " + view);
        System.out.println("Создание заявки:");
        Item item = new Item(1, "Java");
        System.out.println(item);
    }
}

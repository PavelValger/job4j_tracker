package ru.job4j.oop;

public class MoveVehicle {
    public static void main(String[] args) {
        Vehicle boeing = new Airplane();
        Vehicle airbus = new Airplane();
        Vehicle lastochka = new Train();
        Vehicle express = new Train();
        Vehicle intercity = new Bus();
        Vehicle urban = new Bus();
        Vehicle[] vehicles = new Vehicle[]{boeing, airbus, lastochka, express, intercity, urban};
        for (int index = 0; index < vehicles.length; index++) {
            if (index % 2 == 0) {
                vehicles[index].move();
            } else {
                vehicles[index].speed();
            }
        }
    }
}

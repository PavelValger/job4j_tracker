package ru.job4j.oop;

public class MoveVehicle {
    public static void main(String[] args) {
        Vehicle boeing = new Airplane();
        Vehicle airbus = new Airplane();
        Vehicle lastochka = new Train();
        Vehicle express = new Train();
        Vehicle intercity = new Bus();
        Vehicle urban = new Bus();
        Vehicle[] vehicles = new Vehicle[]{boeing, lastochka, intercity};
        for (Vehicle transport : vehicles) {
            transport.move();
        }
        Vehicle[] speed = new Vehicle[]{airbus, express, urban};
        for (Vehicle transport : speed) {
            transport.speed();
        }
    }
}

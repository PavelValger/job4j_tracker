package ru.job4j.oop;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " flying through the air");
    }

    @Override
    public void speed() {
        System.out.println(getClass().getSimpleName() + " reaches a speed of up to 800 km/h");
    }
}

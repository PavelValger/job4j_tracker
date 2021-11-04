package ru.job4j.oop;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " moving on high-speed highways");
    }

    @Override
    public void speed() {
        System.out.println(getClass().getSimpleName() + " reaches a speed of up to 90 km/h");
    }
}

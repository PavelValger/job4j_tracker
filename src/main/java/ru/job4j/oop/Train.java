package ru.job4j.oop;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " moves on rails");
    }

    @Override
    public void speed() {
        System.out.println(getClass().getSimpleName() + " reaches a speed of up to 140 km/h");
    }
}

package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void drive() {
        System.out.println("Автобус отправляется");
    }

    @Override
    public void passengers(int pass) {
        if (pass > 0 && pass < 26) {
            drive();
        }
    }

    @Override
    public int refuel(int fuel) {
        return 50 * fuel;
    }
}

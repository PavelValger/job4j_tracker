package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.calculator.PlacesCalculator;

public class ParkingAtIkea extends AbstractParking {
    private int sizeParkMotoCar;
    private int sizeParkTruck;
    private PlacesCalculator calculator;

    public ParkingAtIkea(int sizeParkMotoCar, int sizeParkTruck, PlacesCalculator calculator) {
        this.sizeParkMotoCar = sizeParkMotoCar;
        this.sizeParkTruck = sizeParkTruck;
        this.calculator = calculator;
    }

    @Override
    protected int getSizeParkMotoCar() {
        return sizeParkMotoCar;
    }

    @Override
    protected int getSizeParkTruck() {
        return sizeParkTruck;
    }
}

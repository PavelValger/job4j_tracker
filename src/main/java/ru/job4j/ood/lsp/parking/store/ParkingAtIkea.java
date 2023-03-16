package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.TransportMeans;

public class ParkingAtIkea extends AbstractParking {
    private static final String TRUCK = "Truck";
    private static final String MOTORCAR = "Motorcar";
    private static final int PASSENGER_CAR_SIZE = 1;
    private int sizeParkMotoCar;
    private int sizeParkTruck;

    public ParkingAtIkea(int sizeParkMotoCar, int sizeParkTruck) {
        this.sizeParkMotoCar = sizeParkMotoCar;
        this.sizeParkTruck = sizeParkTruck;
    }

    @Override
    protected String calculation(TransportMeans transportMeans) {
        String rsl = "";
        int carSize = transportMeans.getCarSize();
        boolean passengerCar = carSize == PASSENGER_CAR_SIZE;
        boolean motorcar = passengerCar && sizeParkMotoCar > 0;
        boolean placeTruck = sizeParkTruck > 0;
        boolean truck = !passengerCar && placeTruck;
        boolean truckNoPlace = !passengerCar && !placeTruck;
        if (truck) {
            rsl = TRUCK;
            sizeParkTruck--;
        }
        if (truckNoPlace && sizeParkMotoCar - carSize >= 0) {
            rsl = MOTORCAR;
            sizeParkMotoCar -= carSize;
        }
        if (motorcar) {
            rsl = MOTORCAR;
            sizeParkMotoCar--;
        }
        if (rsl.isEmpty()) {
            throw new IllegalArgumentException("Парковочных мест нет!");
        }
        return rsl;
    }
}

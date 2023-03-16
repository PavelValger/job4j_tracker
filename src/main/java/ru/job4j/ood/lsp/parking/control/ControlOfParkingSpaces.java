package ru.job4j.ood.lsp.parking.control;

import ru.job4j.ood.lsp.parking.model.TransportMeans;
import ru.job4j.ood.lsp.parking.store.Parking;

public class ControlOfParkingSpaces {
    private final Parking parking;

    public ControlOfParkingSpaces(Parking parking) {
        this.parking = parking;
    }

    public void park(TransportMeans transportMeans) {
        parking.add(transportMeans);
    }
}

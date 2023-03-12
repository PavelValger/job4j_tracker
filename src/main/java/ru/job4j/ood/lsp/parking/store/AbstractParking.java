package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.TransportMeans;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractParking implements Parking {
    private List<TransportMeans> listMotoCar = new LinkedList<>();
    private List<TransportMeans> listTruck = new LinkedList<>();

    protected abstract int getSizeParkMotoCar();

    protected abstract int getSizeParkTruck();

    @Override
    public boolean add(TransportMeans transportMeans) {
        return false;
    }

    @Override
    public String getSizeAllParkingSpaces() {
        return null;
    }
}

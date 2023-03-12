package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.TransportMeans;

public interface Parking {
    boolean add(TransportMeans transportMeans);

    String getSizeAllParkingSpaces();
}

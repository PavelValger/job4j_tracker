package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.TransportMeans;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class AbstractParking implements Parking {
    private final Map<String, LinkedList<TransportMeans>> parking = new HashMap<>();

    protected abstract String calculation(TransportMeans transportMeans);

    @Override
    public boolean add(TransportMeans transportMeans) {
        String calc = calculation(transportMeans);
        var keySearch = parking.putIfAbsent(calc, new LinkedList<>());
        var value = parking.get(calc);
        if (value.isEmpty() || keySearch != null) {
            value.add(transportMeans);
        }
        return true;
    }

    @Override
    public String getSizeAllParkingSpaces() {
        return parking.toString();
    }
}

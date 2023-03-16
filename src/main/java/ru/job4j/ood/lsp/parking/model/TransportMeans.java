package ru.job4j.ood.lsp.parking.model;

import java.util.Objects;

public abstract class TransportMeans {
    private final String name;
    private final int carSize;

    public TransportMeans(String name, int carSize) {
        this.name = name;
        this.carSize = carSize;
    }

    public String getName() {
        return name;
    }

    public int getCarSize() {
        return carSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransportMeans that = (TransportMeans) o;
        return carSize == that.carSize && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, carSize);
    }

    @Override
    public String toString() {
        return "TransportMeans{"
                + "name='" + name + '\''
                + ", carSize=" + carSize
                + '}';
    }
}

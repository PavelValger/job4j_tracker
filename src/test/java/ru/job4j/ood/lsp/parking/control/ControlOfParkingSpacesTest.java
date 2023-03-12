package ru.job4j.ood.lsp.parking.control;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.calculator.CarPlacesCalculator;
import ru.job4j.ood.lsp.parking.calculator.PlacesCalculator;
import ru.job4j.ood.lsp.parking.model.Motorcar;
import ru.job4j.ood.lsp.parking.model.TransportMeans;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.store.Parking;
import ru.job4j.ood.lsp.parking.store.ParkingAtIkea;

import static org.assertj.core.api.Assertions.*;
@Ignore
class ControlOfParkingSpacesTest {

    @Test
    void whenAddParkingAtIkea() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        TransportMeans motoCarVesta = new Motorcar("Vesta", 1);
        TransportMeans motoCarGranta = new Motorcar("Granta", 1);
        PlacesCalculator calculator = new CarPlacesCalculator();
        Parking parking = new ParkingAtIkea(2, 1, calculator);
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(motoCarVesta)).isTrue();
        assertThat(parking.add(motoCarGranta)).isTrue();
    }

    @Test
    void whenAddParkingAtIkeaMotoCarThenException() {
        TransportMeans motoCarVesta = new Motorcar("Vesta", 1);
        PlacesCalculator calculator = new CarPlacesCalculator();
        Parking parking = new ParkingAtIkea(0, 0, calculator);
        assertThatThrownBy(() -> parking.add(motoCarVesta))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAddParkingAtIkeaTruckThenException() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        PlacesCalculator calculator = new CarPlacesCalculator();
        Parking parking = new ParkingAtIkea(0, 0, calculator);
        assertThatThrownBy(() -> parking.add(truck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPark() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        TransportMeans motoCar = new Motorcar("Vesta", 1);
        PlacesCalculator calculator = new CarPlacesCalculator();
        Parking parking = new ParkingAtIkea(2, 2, calculator);
        ControlOfParkingSpaces control = new ControlOfParkingSpaces(parking);
        control.park(motoCar);
        control.park(truck);
        String st = "Количество свободных парковочных мест для";
        assertThat(parking.getSizeAllParkingSpaces()).isEqualTo(String.format(
                "%s легковых машин = %d, %s грузовых машин= %d", st, 1, st, 1));
    }

    @Test
    void whenParkTruckInParkMotoCar() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        TransportMeans motoCar = new Motorcar("Vesta", 1);
        PlacesCalculator calculator = new CarPlacesCalculator();
        Parking parking = new ParkingAtIkea(6, 0, calculator);
        ControlOfParkingSpaces control = new ControlOfParkingSpaces(parking);
        control.park(motoCar);
        control.park(truck);
        String st = "Количество свободных парковочных мест для";
        assertThat(parking.getSizeAllParkingSpaces()).isEqualTo(String.format(
                "%s легковых машин = %d, %s грузовых машин= %d", st, 1, st, 0));
    }
}

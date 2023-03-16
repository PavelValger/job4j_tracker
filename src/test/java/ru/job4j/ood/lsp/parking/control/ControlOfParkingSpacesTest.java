package ru.job4j.ood.lsp.parking.control;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Motorcar;
import ru.job4j.ood.lsp.parking.model.TransportMeans;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.store.Parking;
import ru.job4j.ood.lsp.parking.store.ParkingAtIkea;

import static org.assertj.core.api.Assertions.*;

class ControlOfParkingSpacesTest {

    @Test
    void whenAddParkingAtIkea() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        TransportMeans motoCarVesta = new Motorcar("Vesta", 1);
        TransportMeans motoCarGranta = new Motorcar("Granta", 1);
        Parking parking = new ParkingAtIkea(2, 1);
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(motoCarVesta)).isTrue();
        assertThat(parking.add(motoCarGranta)).isTrue();
    }

    @Test
    void whenAddParkingAtIkeaMotoCarThenException() {
        TransportMeans motoCarVesta = new Motorcar("Vesta", 1);
        Parking parking = new ParkingAtIkea(0, 0);
        assertThatThrownBy(() -> parking.add(motoCarVesta))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAddParkingAtIkeaTruckThenException() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        Parking parking = new ParkingAtIkea(0, 0);
        assertThatThrownBy(() -> parking.add(truck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPark() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        TransportMeans motoCar = new Motorcar("Vesta", 1);
        Parking parking = new ParkingAtIkea(2, 2);
        ControlOfParkingSpaces control = new ControlOfParkingSpaces(parking);
        control.park(motoCar);
        control.park(truck);
        assertThat(parking.getSizeAllParkingSpaces())
                .isEqualTo("{Truck=[TransportMeans{name='KAMAZ', carSize=4}],"
                        + " Motorcar=[TransportMeans{name='Vesta', carSize=1}]}");
    }

    @Test
    void whenParkTruckInParkMotoCar() {
        TransportMeans truck = new Truck("KAMAZ", 4);
        TransportMeans motoCar = new Motorcar("Vesta", 1);
        Parking parking = new ParkingAtIkea(6, 0);
        ControlOfParkingSpaces control = new ControlOfParkingSpaces(parking);
        control.park(motoCar);
        control.park(truck);
        assertThat(parking.getSizeAllParkingSpaces())
                .isEqualTo("{Motorcar=[TransportMeans{name='Vesta', carSize=1},"
                        + " TransportMeans{name='KAMAZ', carSize=4}]}");
    }
}

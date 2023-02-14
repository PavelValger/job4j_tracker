package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Cheese;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenAddWarehouse() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 20, 14, 0),
                500f, 0.5f);
        AbstractStore warehouse = new Warehouse();
        warehouse.add(cheese, 10f);
        warehouse.add(cheese, 10f);
        assertThat(warehouse.getList().size()).isEqualTo(2);
        assertThat(warehouse.getList().get(0)).isEqualTo(cheese);
    }

    @Test
    void whenAddTrash() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 12, 14, 0),
                500f, 0.5f);
        AbstractStore trash = new Trash();
        trash.add(cheese, 110f);
        trash.add(cheese, 110f);
        assertThat(trash.getList().size()).isEqualTo(2);
        assertThat(trash.getList().get(0)).isEqualTo(cheese);
    }

    @Test
    void whenAddShop() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 15, 14, 0),
                500f, 0.5f);
        LocalDateTime createDate = cheese.getCreateDate();
        LocalDateTime expiryDate = cheese.getExpiryDate();
        LocalDateTime now = LocalDateTime.now();
        float shelfLife = (float) HOURS.between(createDate, expiryDate);
        float storageTime = (float) HOURS.between(createDate, now);
        float foodQuality = (storageTime / shelfLife) * 100;
        AbstractStore shop = new Shop();
        shop.add(cheese, foodQuality);
        assertThat(shop.getList().size()).isEqualTo(1);
        assertThat(shop.getList().get(0).getPrice()).isEqualTo(250f);
        assertThat(shop.getList().get(0).getName()).isEqualTo("Cheese");
    }

    @Test
    void whenDistributeTheFoodAll() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 20, 14, 0),
                500f, 0.5f);
        Food milk = new Cheese("Milk",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 4, 20, 14, 0),
                500f, 0.5f);
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<Store> stores = List.of(
                warehouse,
                shop,
                trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.distribution(cheese);
        cq.distribution(milk);
        assertThat(shop.getList().size()).isEqualTo(1);
        assertThat(trash.getList().size()).isEqualTo(0);
        assertThat(warehouse.getList().size()).isEqualTo(1);
    }

    @Test
    void whenDistributeTheFoodThenException() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 20, 14, 0),
                LocalDateTime.of(2023, 2, 25, 14, 0),
                500f, 0.5f);
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<Store> stores = List.of(
                warehouse,
                shop,
                trash);
        ControlQuality cq = new ControlQuality(stores);
        assertThatThrownBy(() -> cq.distribution(cheese))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenDistributeTheFoodAndStoreIsEmpty() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 20, 14, 0),
                500f, 0.5f);
        List<Store> stores = List.of();
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        ControlQuality cq = new ControlQuality(stores);
        cq.distribution(cheese);
        assertThat(shop.getList().size()).isEqualTo(0);
        assertThat(trash.getList().size()).isEqualTo(0);
        assertThat(warehouse.getList().size()).isEqualTo(0);
    }
}
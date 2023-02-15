package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Barbecue;
import ru.job4j.ood.lsp.model.Cheese;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.model.Milk;
import ru.job4j.ood.lsp.store.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenAddWarehouse() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 15, 14, 0),
                LocalDateTime.of(2023, 2, 20, 14, 0),
                500f, 0.5f);
        AbstractStore warehouse = new Warehouse();
        warehouse.add(cheese);
        warehouse.add(cheese);
        assertThat(warehouse.getAll().size()).isEqualTo(2);
        assertThat(warehouse.getAll().get(0)).isEqualTo(cheese);
    }

    @Test
    void whenAddTrash() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 12, 14, 0),
                500f, 0.5f);
        AbstractStore trash = new Trash();
        trash.add(cheese);
        trash.add(cheese);
        assertThat(trash.getAll().size()).isEqualTo(2);
        assertThat(trash.getAll().get(0)).isEqualTo(cheese);
    }

    @Test
    void whenAddShop() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 16, 14, 0),
                500f, 0.5f);
        AbstractStore shop = new Shop();
        shop.add(cheese);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().get(0).getPrice()).isEqualTo(250f);
        assertThat(shop.getAll().get(0).getName()).isEqualTo("Cheese");
    }

    @Test
    void whenDistributeTheFoodAll() {
        Food cheese = new Cheese("Cheese",
                LocalDateTime.of(2023, 2, 10, 14, 0),
                LocalDateTime.of(2023, 2, 20, 14, 0),
                500f, 0.5f);
        Food milk = new Milk("Milk",
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
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(warehouse.getAll().size()).isEqualTo(1);
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
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(warehouse.getAll().size()).isEqualTo(0);
    }

    @Test
    void whenAddShopAndClear() {
        Food cheese = new Barbecue("Barbecue",
                LocalDateTime.of(2023, 2, 14, 14, 0),
                LocalDateTime.of(2023, 2, 16, 14, 0),
                500f, 0.5f);
        AbstractStore shop = new Shop();
        shop.add(cheese);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().get(0).getPrice()).isEqualTo(500f);
        assertThat(shop.getAll().get(0).getName()).isEqualTo("Barbecue");
        shop.clear();
        assertThat(shop.getAll().size()).isEqualTo(0);
    }
}
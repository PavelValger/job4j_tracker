package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.calculator.FoodExpirationCalculator;
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
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(2),
                time.plusDays(10),
                500f, 0.5f);
        AbstractStore warehouse = new Warehouse(new FoodExpirationCalculator());
        warehouse.add(cheese);
        warehouse.add(cheese);
        assertThat(warehouse.getAll().size()).isEqualTo(2);
        assertThat(warehouse.getAll().get(0)).isEqualTo(cheese);
    }

    @Test
    void whenAddTrash() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(5),
                time.minusDays(1),
                500f, 0.5f);
        AbstractStore trash = new Trash(new FoodExpirationCalculator());
        trash.add(cheese);
        trash.add(cheese);
        assertThat(trash.getAll().size()).isEqualTo(2);
        assertThat(trash.getAll().get(0)).isEqualTo(cheese);
    }

    @Test
    void whenAddShop() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(5),
                time.plusDays(1),
                500f, 0.5f);
        AbstractStore shop = new Shop(new FoodExpirationCalculator());
        shop.add(cheese);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().get(0).getPrice()).isEqualTo(250f);
        assertThat(shop.getAll().get(0).getName()).isEqualTo("Cheese");
    }

    @Test
    void whenDistributeTheFoodAll() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(5),
                time.plusDays(3),
                500f, 0.5f);
        Food milk = new Milk("Milk",
                time.minusDays(3),
                time.plusDays(20),
                500f, 0.5f);
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        AbstractStore warehouse = new Warehouse(calculator);
        AbstractStore shop = new Shop(calculator);
        AbstractStore trash = new Trash(calculator);
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
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.plusDays(1),
                time.plusDays(2),
                500f, 0.5f);
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        AbstractStore warehouse = new Warehouse(calculator);
        AbstractStore shop = new Shop(calculator);
        AbstractStore trash = new Trash(calculator);
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
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(7),
                time.plusDays(3),
                500f, 0.5f);
        List<Store> stores = List.of();
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        AbstractStore warehouse = new Warehouse(calculator);
        AbstractStore shop = new Shop(calculator);
        AbstractStore trash = new Trash(calculator);
        ControlQuality cq = new ControlQuality(stores);
        cq.distribution(cheese);
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(warehouse.getAll().size()).isEqualTo(0);
    }

    @Test
    void whenAddShopAndClear() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Barbecue("Barbecue",
                time.minusDays(7),
                time.plusDays(3),
                500f, 0.5f);
        AbstractStore shop = new Shop(new FoodExpirationCalculator());
        shop.add(cheese);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().get(0).getPrice()).isEqualTo(500f);
        assertThat(shop.getAll().get(0).getName()).isEqualTo("Barbecue");
        shop.clear();
        assertThat(shop.getAll().size()).isEqualTo(0);
    }

    @Test
    void whenDistributionAndResortTheFoodAll() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(5),
                time.plusDays(3),
                500f, 0.5f);
        Food milk = new Milk("Milk",
                time.minusDays(3),
                time.plusDays(20),
                500f, 0.5f);
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        AbstractStore warehouse = new Warehouse(calculator);
        AbstractStore shop = new Shop(calculator);
        AbstractStore trash = new Trash(calculator);
        List<Store> stores = List.of(
                warehouse,
                shop,
                trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.distribution(cheese);
        cq.distribution(milk);
        cq.resort(new TemporaryStore());
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(warehouse.getAll().size()).isEqualTo(1);
    }

    @Test
    void whenShopInResortThenException() {
        LocalDateTime time = LocalDateTime.now();
        Food cheese = new Cheese("Cheese",
                time.minusDays(5),
                time.plusDays(3),
                500f, 0.5f);
        Food milk = new Milk("Milk",
                time.minusDays(3),
                time.plusDays(20),
                500f, 0.5f);
        ExpirationCalculator calculator = new FoodExpirationCalculator();
        AbstractStore warehouse = new Warehouse(calculator);
        AbstractStore shop = new Shop(calculator);
        AbstractStore trash = new Trash(calculator);
        List<Store> stores = List.of(
                warehouse,
                shop,
                trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.distribution(cheese);
        cq.distribution(milk);
        assertThatThrownBy(() -> cq.resort(shop))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
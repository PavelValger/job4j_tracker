package ru.job4j.tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.store.HbmTracker;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

class HbmTrackerTest {
    private static final HbmTracker TRACKER = new HbmTracker();

    @AfterEach
    public void clearItems() {
        for (Item item : TRACKER.findAll()) {
            TRACKER.delete(item.getId());
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item();
        item.setName("test1");
        TRACKER.add(item);
        Item result = TRACKER.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenReplaceItemAndFindByGeneratedIdThenMustBeTheSame() {
        Item item = new Item("item");
        TRACKER.add(item);
        int id = item.getId();
        Item apple = new Item("apple");
        TRACKER.replace(id, apple);
        assertThat(TRACKER.findById(id).getName()).isEqualTo("apple");
    }

    @Test
    public void whenDeleteItemAndFindByGeneratedIdThenNull() {
        Item item = new Item("item");
        TRACKER.add(item);
        int id = item.getId();
        TRACKER.delete(id);
        assertThat(TRACKER.findById(id)).isNull();
    }

    @Test
    public void whenFindAllItemThenMustBeTheSame() {
        Item item = new Item("item");
        Item apple = new Item("apple");
        TRACKER.add(item);
        TRACKER.add(apple);
        assertThat(TRACKER.findAll()).hasSize(2)
                .containsExactly(item, apple);
    }

    @Test
    public void whenFindByNameItemThenMustBeTheSame() {
        Item item = new Item("item");
        Item apple = new Item("apple");
        Item it = new Item("item");
        TRACKER.add(item);
        TRACKER.add(apple);
        TRACKER.add(it);
        assertThat(TRACKER.findByName("item")).hasSize(2)
                .isEqualTo(List.of(item, it));
    }

    @Test
    public void whenFindByIdItemThenNull() {
        Item item = new Item("item");
        TRACKER.add(item);
        assertThat(TRACKER.findById(777)).isNull();
    }
}
package ru.job4j.tracker;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

class SqlTrackerTest {
    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader()
                .getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Disabled
    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Assertions.assertEquals(item, tracker.findById(item.getId()));
    }

    @Test
    public void whenReplaceItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        int id = item.getId();
        Item apple = new Item("apple");
        tracker.replace(id, apple);
        assertThat(tracker.findById(id).getName()).isEqualTo("apple");
    }

    @Test
    public void whenDeleteItemAndFindByGeneratedIdThenNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        Assertions.assertNull(tracker.findById(id));
    }

    @Disabled
    @Test
    public void whenFindAllItemThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item apple = new Item("apple");
        tracker.add(item);
        tracker.add(apple);
        assertThat(tracker.findAll()).hasSize(2)
        .isEqualTo(List.of(item, apple));
    }

    @Disabled
    @Test
    public void whenFindByNameItemThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item apple = new Item("apple");
        Item it = new Item("item");
        tracker.add(item);
        tracker.add(apple);
        tracker.add(it);
        assertThat(tracker.findByName("item")).hasSize(2)
                .isEqualTo(List.of(item, it));
    }

    @Test
    public void whenFindByIdItemThenNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Assertions.assertNull(tracker.findById(777));
    }
}
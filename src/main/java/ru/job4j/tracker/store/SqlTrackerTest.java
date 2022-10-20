package ru.job4j.tracker.store;

import org.junit.*;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class SqlTrackerTest {
    private static Connection connection;

    @BeforeClass
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

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @After
    public void clearIndex() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "ALTER TABLE items ALTER COLUMN id RESTART WITH 1")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenReplaceItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item apple = new Item(1, "apple");
        tracker.add(item);
        int id = item.getId();
        tracker.replace(id, apple);
        Assert.assertEquals(tracker.findById(id).getName(), "apple");
    }

    @Test
    public void whenDeleteItemAndFindByGeneratedIdThenNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        assertNull(tracker.findById(id));
    }

    @Test
    public void whenFindAllItemThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item apple = new Item("apple");
        tracker.add(item);
        tracker.add(apple);
        assertThat(tracker.findAll().get(0), is(item));
        assertThat(tracker.findAll().get(1), is(apple));
        assertThat(tracker.findAll().size(), is(2));
    }

    @Test
    public void whenFindByNameItemThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item apple = new Item("apple");
        Item it = new Item("item");
        tracker.add(item);
        tracker.add(apple);
        tracker.add(it);
        assertThat(tracker.findByName("item").size(), is(2));
        Assert.assertEquals(tracker.findByName("item").get(0).getName(), "item");
    }

    @Test
    public void whenFindByIdItemThenNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertNull(tracker.findById(777));
    }
}

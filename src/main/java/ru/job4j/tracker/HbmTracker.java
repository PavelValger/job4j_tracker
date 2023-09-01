package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    private boolean crud(Consumer<Session> consumer) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            consumer.accept(session);
            closeSession(session);
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return false;
    }

    private <S> S search(Function<Session, S> function) {
        Session session = sf.openSession();
        S result = null;
        try {
            session.beginTransaction();
            result = function.apply(session);
            closeSession(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public Item add(Item item) {
        crud(session -> session.save(item));
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        return crud(session -> session.createQuery(
                        "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                .setParameter("fName", item.getName())
                .setParameter("fCreated", item.getCreated())
                .setParameter("fId", id)
                .executeUpdate());
    }

    @Override
    public boolean delete(int id) {
        return crud(session -> session.createQuery(
                        "DELETE Item WHERE id = :fId")
                .setParameter("fId", id)
                .executeUpdate());
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(search(session -> session
                .createQuery("from Item", Item.class).list()));
    }

    @Override
    public List<Item> findByName(String key) {
        return new ArrayList<>(search(session -> session
                .createQuery("from Item where name = :fName", Item.class)
                .setParameter("fName", key).list()));
    }

    @Override
    public Item findById(int id) {
        return search(session -> session.get(Item.class, id));
    }
}

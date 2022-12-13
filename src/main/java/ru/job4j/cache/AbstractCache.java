package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        SoftReference<V> softReference = cache.getOrDefault(key, new SoftReference<>(null));
        V strongReference = softReference.get();
        boolean empty = strongReference == null;
        V loading = null;
        if (empty) {
            loading = load(key);
            put(key, loading);
            System.out.println("Загрузка данных в кэш прошла успешно");
        }
        return empty ? loading : strongReference;
    }

    protected abstract V load(K key);
}

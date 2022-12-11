package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.putIfAbsent(key, new SoftReference<>(value));
    }

    public V get(K key) {
        var softReference = cache.get(key);
        V strongReference = softReference != null ? softReference.get() : load(key);
        return strongReference != null ? strongReference : load(key);
    }

    public abstract V load(K key);
}

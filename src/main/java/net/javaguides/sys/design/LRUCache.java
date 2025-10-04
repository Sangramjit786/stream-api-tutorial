package net.javaguides.sys.design;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int maxSize;
    private final LinkedHashMap<K, V> cache;

    public LRUCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Cache size must be greater than 0");
        }
        this.maxSize = maxSize;
        // Initialize LinkedHashMap with accessOrder=true to maintain access order
        this.cache = new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.maxSize;
            }
        };
    }

    public synchronized V get(K key) {
        return cache.get(key);
    }

    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }

    public synchronized int size() {
        return cache.size();
    }

    public synchronized boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public synchronized void clear() {
        cache.clear();
    }

    @Override
    public synchronized String toString() {
        return cache.toString();
    }
}

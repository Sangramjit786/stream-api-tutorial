package net.javaguides.sys.design;

public class Main {
    public static void main(String[] args) {
        // Create a cache with maximum 3 entries
        LRUCache<Integer, Object> cache = new LRUCache<>(3);

        // Add entries
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        // Access some entries
        System.out.println(cache.get(1)); // Moves 1 to most recently used

        // Add another entry - this will evict the least recently used (2)
        cache.put(4, "Four");

        System.out.println(cache); // Will show {3=Three, 1=One, 4=Four}
    }
}

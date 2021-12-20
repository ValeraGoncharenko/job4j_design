package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (((float) count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        int i = indexFor(hash(key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode % capacity;
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                tempTable[i] = table[i];
            }
        }
        table = Arrays.copyOf(tempTable, capacity);
    }

    @Override
    public V get(K key) {
        V result = null;
        int i = indexFor(hash(key.hashCode()));
        if (table[i] != null && Objects.equals(key, table[i].key)) {
            result = table[i].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int i = indexFor(hash(key.hashCode()));
        if (table[i] != null && Objects.equals(key, table[i].key)) {
            table[i] = null;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int point = 0;
            private int size = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                size++;
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

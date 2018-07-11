package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<K> {

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int arrayLength = 16;
    private int size = 0;
    private int modCount = 0;

    public SimpleHashMap() {
        this.table = (Node<K, V>[]) new Node[arrayLength];
    }

    public boolean insert(K key, V value) {
        if ((size / arrayLength) > DEFAULT_LOAD_FACTOR) {
            resize();
        }
        if (table[hash(key)] == null) {
            table[hash(key)] = new Node<>(key, value);
            size++;
            modCount++;
            return true;
        }
        return false;
    }


    public V get(K key) {
        if (table[hash(key)] != null) {
            return table[hash(key)].getValue();
        }
        return null;
    }

    public boolean delete(K key) {
        if (table[hash(key)] != null) {
            table[hash(key)] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }


    private void resize() {
        arrayLength = arrayLength * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[arrayLength];
        for (Node<K, V> data : this.table) {
            if (data != null) {
                newTable[hash(data.getKey())] = data;
            }
        }
        this.table = newTable;
    }

    private int hash(K key) {
        return key.hashCode() % arrayLength;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;
            int index = 0;
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public K next() {
                checkForComodification();
                K result = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i + 1;
                        counter++;
                        result = table[i].getKey();
                        break;
                    }
                }
                return result;
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

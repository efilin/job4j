package ru.job4j.map;

import java.util.Iterator;

public class SimpleHashMap <K, V> implements Iterable <K> {

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int DEFAULT_INITIAL_CAPACITY = 10;

    private int size;
    private int count = 0;
    private float logFactor = 0;





    boolean insert(K key, V value) {

        return false;
    }


    V get(K key) {

        return null;
    }

    boolean delete(K key) {

        return false;
    }


    void resize() {

    }






    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }
}

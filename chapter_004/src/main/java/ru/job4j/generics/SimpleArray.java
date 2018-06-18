package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] objects;
    private int position = 0;

    SimpleArray(int size) {
        this.objects = new Object[size];
    }

    void add(T model) {
        this.objects[position++] = model;
    }

    void set(int index, T model) {
        if (index >= position) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.objects[index] = model;
    }

    void delete(int index) {
        if (index >= position) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < position - 1; i++) {
            this.objects[i] = this.objects[i + 1];
        }
        this.objects[position - 1] = null;
    }

    T get(int index) {
        if (index >= position) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < position;
            }

            @Override
            public T next() {
                return (T) objects[index++];
            }
        };
    }
}

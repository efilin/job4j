package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {

    Object[] objects;
    private int position = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        this.objects[position++] = model;
    }

    public void set(int index, T model) {
        if (index < position) {
            this.objects[index] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void delete(int index) {
        if (index < position) {
            for (int i = index; i < position - 1; i++) {
                this.objects[i] = this.objects[i + 1];
            }
            this.objects[position - 1] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public T get(int index) {
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

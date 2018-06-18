package ru.job4j.generics;

public class AbstractStore<T extends Base> implements Store<T> {

    T[] store;
    int position = 0;


    @Override
    public void add(T model) {
        this.store[position++] = model;

    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < position; i++) {
            if (id.equals(this.store[i].getId())) {
                result = true;
                this.store[i] = model;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < position; i++) {
            if (id.equals(this.store[i].getId())) {
                result = true;

            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return null;
    }
}

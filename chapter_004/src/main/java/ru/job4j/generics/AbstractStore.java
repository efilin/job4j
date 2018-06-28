package ru.job4j.generics;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private T[] store;
    private int position = 0;

    public void setStore(T[] store) {
        this.store = store;
    }

    public void setPosition(int position) {
        this.position = position;
    }

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
            } else if (result) {
                this.store[i - 1] = this.store[i];
                this.store[position - 1] = null;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (int i = 0; i < position; i++) {
            if (id.equals(this.store[i].getId())) {
                result = this.store[i];
                break;
            }
        }
        return result;
    }
}

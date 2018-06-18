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


        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public T findById(String id) {
        return null;
    }
}

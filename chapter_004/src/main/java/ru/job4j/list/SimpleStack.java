package ru.job4j.list;

public class SimpleStack<T> {
    private SimpleArrayList<T> container = new SimpleArrayList<>();

    public T poll() {
        return this.container.delete();
    }

    public void push(T value) {
        this.container.add(value);
    }

}

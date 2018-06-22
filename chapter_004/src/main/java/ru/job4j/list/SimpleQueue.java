package ru.job4j.list;

public class SimpleQueue<T> {
    private LinkedListContainer<T> queue = new LinkedListContainer<>();

    public T poll() {
        return this.queue.delete();
    }

    public void push(T value) {
        this.queue.add(value);
    }
}

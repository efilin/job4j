package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private static final int QUEUE_MAX_SIZE = 10;

    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {

        while (queue.size() >= QUEUE_MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        notify();
    }

    public synchronized boolean isEmpty() {
        return this.queue.size() == 0;
    }

    public synchronized T poll() throws InterruptedException {
        T result;
        while (queue.size() == 0) {
            wait();
        }
        result = queue.poll();
        notify();
        return result;
    }
}


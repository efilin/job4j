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

    public synchronized void offer(T value) throws InterruptedException {

        while (queue.size() >= QUEUE_MAX_SIZE) {
            wait();
        }
        queue.offer(value);
        System.out.println("Offer " + value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        T result;
        while (queue.size() == 0) {
            wait();
        }
        result = queue.poll();
        System.out.println("Poll " + result);
        notify();
        return result;
    }
}


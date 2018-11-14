package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

public class DeadLock {

    private final CountDownLatch latch = new CountDownLatch(2);

    public void call(final Object left, final Object right) {
        try {
            synchronized (left) {
                latch.countDown();
                this.latch.await();
                synchronized (right) {
                    System.out.println("Can't reach there!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        final DeadLock dead = new DeadLock();
        final Object left = new Object();
        final Object right = new Object();
        new Thread(
                () -> dead.call(left, right)
        ).start();
        new Thread(
                () -> dead.call(right, left)
        ).start();
    }
}

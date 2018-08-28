package ru.job4j.waitnotify;

import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleBlockingQueueTest {

    @Test
    public void whenTwoThreads() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();

        Thread producer = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 40) {
                    simpleBlockingQueue.offer(i++);

                }
            }
        };

        Thread customer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    try {
                        simpleBlockingQueue.poll();
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException");
                    }
                }
            }
        };
        producer.start();
        customer.start();
        producer.join();
        customer.join();
    }
}
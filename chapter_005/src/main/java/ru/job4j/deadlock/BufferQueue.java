package ru.job4j.deadlock;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class BufferQueue {
    public List<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        BufferQueue queue = new BufferQueue();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        new ThreadOne(countDownLatch, queue);
        new ThreadTwo(countDownLatch, queue);
    }
}

class ThreadOne implements Runnable {
    private BufferQueue queue;
    private CountDownLatch countDownLatch;

    ThreadOne(CountDownLatch countDownLatch, BufferQueue q) {
        this.countDownLatch = countDownLatch;
        this.queue = q;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            queue.buffer.add(1);
            System.out.println("ThreadOne add 1 to buffer");
            countDownLatch.countDown();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadTwo implements Runnable {
    private BufferQueue queue;
    private CountDownLatch countDownLatch;

    ThreadTwo(CountDownLatch countDownLatch, BufferQueue q) {
        this.countDownLatch = countDownLatch;
        this.queue = q;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            queue.buffer.add(2);
            System.out.println("ThreadTwo add 2 to buffer");
        }
    }
}







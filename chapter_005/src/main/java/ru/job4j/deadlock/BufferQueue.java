package ru.job4j.deadlock;


import java.util.LinkedList;
import java.util.List;

public class BufferQueue {
    private List<Integer> buffer = new LinkedList<>();
    private boolean threadOneCanWork = true;

    public static void main(String[] args) {
        BufferQueue queue = new BufferQueue();

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    while (queue.threadOneCanWork) {
                        for (int i = 0; i < 10; i++) {
                            queue.buffer.add(1);
                        }
                        queue.threadOneCanWork = false;
                    }
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    while (!queue.threadOneCanWork) {
                        for (int i = 0; i < 10; i++) {
                            queue.buffer.add(2);
                        }
                        queue.threadOneCanWork = true;
                    }
                }
            }
        });

        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}








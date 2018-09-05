package ru.job4j.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@ThreadSafe
public class ThreadPool {

    @GuardedBy("this")
    private final List<MyThread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>(10);

    private boolean isRunning = true;

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new MyThread());
            threads.get(i).start();
        }
    }

    public void work(Runnable job) {
        synchronized (tasks) {
            tasks.offer(job);
            tasks.notifyAll();
        }
    }

    public synchronized void shutdown() {
        isRunning = false;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                while (tasks.isEmpty()) {
                    synchronized (tasks) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Stopped");
                        }
                    }
                }
                Runnable nextTask = tasks.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}

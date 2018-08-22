package ru.job4j.jmm;

public class MultiThreadingProblemsExample {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            IncrementalThread iT = new IncrementalThread(counter);
            iT.start();
        }
        Thread.sleep(10000);
        System.out.println(counter.getCounter());
    }
}

class IncrementalThread extends Thread {

    private Counter counter;

    public IncrementalThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.increaseCounter();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Counter {

    private int counter = 0;

    public void increaseCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
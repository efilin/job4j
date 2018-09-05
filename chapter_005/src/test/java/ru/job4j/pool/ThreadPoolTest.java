package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    ThreadPool threadPool;


    @Test
    public void whenWorkThenShutDown() {
        Thread mainThread = Thread.currentThread();
        threadPool = new ThreadPool();
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final List<Integer> expected = new ArrayList<>();

        threadPool.work(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    buffer.add(i);
                    System.out.println(i);
                }
            }
        });
        try {
            mainThread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();

        for (int index = 0; index < 200; index++) {
            expected.add(index, index);
        }

        assertThat(buffer, is(expected));

    }


}
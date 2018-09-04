package ru.job4j.nonblocking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NonBlockingCacheTest {

    private NonBlockingCache cache;
    private Base model;

    @Before
    public void setUp() {
        cache = new NonBlockingCache();
        model = new Base(1, "Alex");
        cache.add(model);
    }

    @Test
    public void whenTwoThreadsTryToUpdate() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cache.update(new Base(1, "Lex"));
                } catch (OptimisticException oe) {
                    ex.set(oe);
                }
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    cache.update(new Base(1, "Rex"));
                } catch (OptimisticException oe) {
                    ex.set(oe);
                }
            }
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        Assert.assertThat(ex.get().getMessage(), is("Текущая версия не совпадает с обновляемой, в обновлении отказано!"));
    }
}
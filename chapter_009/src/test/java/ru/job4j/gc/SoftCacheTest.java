package ru.job4j.gc;

import com.google.common.base.Joiner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SoftCacheTest {
    private final ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String LN = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(tempOut));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void whenShowsStartMenu() throws IOException {
        String input = "invalidnamefile.txt" + LN + "exit" + LN;
        ByteArrayInputStream tempIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(tempIn);
        SoftCache cache = new SoftCache(System.getProperty("java.io.tmpdir"), new Scanner(System.in));
        cache.show();
        String expected = Joiner.on(LN)
                .join("Программа с Кэшем на SoftReference",
                        "Программа считывает данные из кэша по имени файла",
                        "если данных в кэше нет, записывает в кэш из файла",
                        "Для выхода из программы введите exit",
                        "Введите имя файла:",
                        "Файл не найден, введите правильное имя файла.",
                        "null",
                        "Введите имя файла:",
                        "Файл не найден, введите правильное имя файла.",
                        "null",
                        "");
        System.setIn(System.in);
        assertThat(tempOut.toString(), is(expected));
    }
}
package ru.job4j.isp;

import com.google.common.base.Joiner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UITest {
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
    public void whenShowsStartMenu() {
        Menu menu = new Menu();
        menu.init();
        menu.showMenu();
        String expected = Joiner.on(LN)
                .join("Task 1",
                        "----Task 1.1",
                        "--------Task 1.1.1",
                        "----Task 1.2",
                        "Task 2",
                        "Введите номер задачи.",
                        "Для выхода введите exit",
                        ""
                );
        assertThat(tempOut.toString(), is(expected));
    }

    @Test
    public void whenInputOneDotTwoAndTaskInfo() {
        String input = "1.2" + LN + "exit" + LN;
        ByteArrayInputStream tempIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(tempIn);
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.init();
        UI ui = new UI(scanner, menu);
        ui.start();
        String expected = "Do Task 1.2" + LN;
        System.setIn(System.in);
        assertThat(tempOut.toString(), is(expected));
    }
}
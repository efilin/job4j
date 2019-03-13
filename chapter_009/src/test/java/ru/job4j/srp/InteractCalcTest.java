package ru.job4j.srp;

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

public class InteractCalcTest {
    private final ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    public static final String LN = System.lineSeparator();

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
        InteractCalc iCalc = new InteractCalc(new Scanner(System.in), new Calculator());
        iCalc.showStartMenu();
        String expected = Joiner.on(LN)
                .join("Вас приветствует программа калькулятор",
                        "Введите выражение для вычисления",
                        "число оператор число",
                        "Если хотите воспользоваться результатом предыдущего вычисления введите r",
                        "Для закрытия программы введите exit",
                        ""
                );
        assertThat(tempOut.toString(), is(expected));
    }

    @Test
    public void whenInputFivePlusTwoAndReturnSeven() {
        String input = "5 + 2" + LN + "exit" + LN;
        ByteArrayInputStream tempIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(tempIn);
        Scanner scanner = new Scanner(System.in);
        InteractCalc iCalc = new InteractCalc(scanner, new Calculator());
        String expected = "7.0" + LN;
        iCalc.init();
        iCalc.menu();
        System.setIn(System.in);
        assertThat(tempOut.toString(), is(expected));
    }

    @Test
    public void whenInputFiveMinusTwoAndReturnThree() {
        String input = "5 - 2" + LN + "exit" + LN;
        ByteArrayInputStream tempIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(tempIn);
        Scanner scanner = new Scanner(System.in);
        InteractCalc iCalc = new InteractCalc(scanner, new Calculator());
        String expected = "3.0" + LN;
        iCalc.init();
        iCalc.menu();
        System.setIn(System.in);
        assertThat(tempOut.toString(), is(expected));
    }

    @Test
    public void whenInputFiveMultipleTwoAndReturnThree() {
        String input = "5 * 2" + LN + "exit" + LN;
        ByteArrayInputStream tempIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(tempIn);
        Scanner scanner = new Scanner(System.in);
        InteractCalc iCalc = new InteractCalc(scanner, new Calculator());
        String expected = "10.0" + LN;
        iCalc.init();
        iCalc.menu();
        System.setIn(System.in);
        assertThat(tempOut.toString(), is(expected));
    }

    @Test
    public void whenInputSixDivideThreeAndReturnTwo() {
        String input = "6 / 3" + LN + "exit" + LN;
        ByteArrayInputStream tempIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(tempIn);
        Scanner scanner = new Scanner(System.in);
        InteractCalc iCalc = new InteractCalc(scanner, new Calculator());
        String expected = "2.0" + LN;
        iCalc.init();
        iCalc.menu();
        System.setIn(System.in);
        assertThat(tempOut.toString(), is(expected));
    }

}
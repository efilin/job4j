package ru.job4j.srp;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Class for UI of Calculator.
 */
public class InteractCalc {
    /**
     * Scanner for reading of input.
     */
    private final Scanner scanner;
    private final Calculator calculator;

    /**
     * String value for return result of last operation.
     */
    private static final String LAST_RESULT = "r";

    /**
     * Double value for return result of last operation.
     */
    private Double result;

    /**
     * String value for exit from main loop.
     */
    private static final String EXIT = "exit";
    /**
     * String value for separate lines.
     */
    private static final String LN = System.lineSeparator();

    /**
     * Default constructor.
     *
     * @param scanner Scanner.
     */
    public InteractCalc(final Scanner scanner, final Calculator calculator) {
        this.scanner = scanner;
        calculator.init();
        this.calculator = calculator;
    }

    /**
     * Shows start menu.
     */
    public void showStartMenu() {
        String startMenu = Joiner.on(LN)
                .join("Вас приветствует программа калькулятор",
                        "Введите выражение для вычисления",
                        "число оператор число",
                        "Если хотите воспользоваться результатом предыдущего вычисления введите r",
                        "Для закрытия программы введите exit"
                );
        System.out.println(startMenu);
    }

    /**
     * Reads input.
     */
    public void menu() {
        String input;
        do {
            input = this.scanner.nextLine();
            if (!input.equals(EXIT)) {
                System.out.println(calculate(parseInput(input)));
            }
        } while (!input.equals(EXIT));
    }

    /**
     * Parses input String by space into String array.
     *
     * @param input Input String from console.
     * @return Parsed array of strings.
     */
    public String[] parseInput(String input) {
        String[] strings = input.split("\\s+");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(LAST_RESULT)) {
                strings[i] = String.valueOf(this.result);
            }
        }
        return strings;
    }


    /**
     * Calculates result of operation.
     *
     * @param strings Parsed Array of Strings.
     * @return Result of operation.
     */
    public Double calculate(String[] strings) {
        double firstNumber = Double.valueOf(strings[0]);
        double secondNumber = Double.valueOf(strings[2]);
        String action = strings[1];
        if (!this.calculator.getFunctionMap().containsKey(action)) {
            throw new UnsupportedOperationException("Математическое действие не разпознано.");
        }
        this.result = this.calculator.getFunctionMap().get(action).apply(firstNumber, secondNumber);
        return this.result;

    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc(new Scanner(System.in), new EngiCalculator(new HashMap<>()));
        interactCalc.showStartMenu();
        interactCalc.menu();
    }
}

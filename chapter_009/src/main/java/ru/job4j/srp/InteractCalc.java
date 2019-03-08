package ru.job4j.srp;

import com.google.common.base.Joiner;

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
    public static final String LAST_RESULT = "r";
    /**
     * String value for exit from main loop.
     */
    public static final String EXIT = "exit";
    /**
     * String value for separate lines.
     */
    public static final String LN = System.lineSeparator();

    /**
     * Default constructor.
     * @param scanner Scanner.
     * @param calculator Calculator.
     */
    public InteractCalc(final Scanner scanner, final Calculator calculator) {
        this.scanner = scanner;
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
                strings[i] = String.valueOf(calculator.getResult());
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
        if (action.equals("+")) {
            calculator.add(firstNumber, secondNumber);
        } else if (action.equals("-")) {
            calculator.subtract(firstNumber, secondNumber);
        } else if (action.equals("*")) {
            calculator.multiple(firstNumber, secondNumber);
        } else if (action.equals("/")) {
            calculator.div(firstNumber, secondNumber);
        } else {
            System.out.println("Математическое действие не разпознано.");
        }
        return calculator.getResult();
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc(new Scanner(System.in), new Calculator());
        interactCalc.showStartMenu();
        interactCalc.menu();
    }
}

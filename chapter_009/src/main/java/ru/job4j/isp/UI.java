package ru.job4j.isp;

import java.util.Scanner;

public class UI {
    public static final String EXIT = "exit";
    private Scanner scanner;
    private Menu menu;

    public UI(Scanner scanner, Menu menu) {
        this.scanner = scanner;
        this.menu = menu;
    }

    public void start() {
        String input;
        do {
            input = this.scanner.nextLine();
            if (this.menu.getMap().containsKey(input)) {
                this.menu.getMap().get(input).action();
            } else if (!input.equals(EXIT)) {
                System.out.println("Номер задачи не распознан");
            }
        } while (!input.equals(EXIT));
    }
}

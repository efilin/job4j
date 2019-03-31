package ru.job4j.tictactoe;

import java.util.Scanner;

public class UI {

    private Scanner scanner;
    private Logic logic;
    private boolean isHumanFirst;
    private static final String START_MESSAGE = "Программа крестики-нолики";
    private static final String CHOOSE_MOVE = "Введите ваш ход";
    private static final String CHOOSE_COLUMN = "Введите номер столбца:";
    private static final String CHOOSE_ROW = "Введите номер строки:";
    private static final String NO_MOVES = "Ходов не осталось";
    private static final String EXIT = "exit";
    private static final char CROSS = 'X';
    private static final char ZERO = 'O';


    public UI(boolean isHumanFirst, Scanner scanner, Logic logic) {
        this.isHumanFirst = isHumanFirst;
        this.scanner = scanner;
        this.logic = logic;
    }

    public void show() {
        String input;
        String column;
        String row;
        System.out.println(START_MESSAGE);
        this.logic.printField();
        do {
            if (!isHumanFirst) {
                this.logic.computerMove();
                this.logic.printField();
                this.isHumanFirst = true;
            }
            System.out.println(CHOOSE_MOVE);
            System.out.println(CHOOSE_COLUMN);
            input = this.scanner.nextLine();
            column = input;
            System.out.println(CHOOSE_ROW);
            input = this.scanner.nextLine();
            row = input;
            if (this.logic.inputValidate(column, row)) {
                this.logic.setX(Integer.parseInt(column), Integer.parseInt(row));
                this.logic.printField();
                if (this.logic.isWin(CROSS)) {
                    System.out.println("Крестики победили");
                    break;
                }
                if (!this.logic.hasGap()) {
                    System.out.println(NO_MOVES);
                    break;
                }
                this.logic.computerMove();
                this.logic.printField();
                if (this.logic.isWin(ZERO)) {
                    System.out.println("Нолики победили");
                    break;
                }
            } else {
                System.out.println("Некорректный ввод, попробуйте еще раз");
            }
            if (!this.logic.hasGap()) {
                System.out.println(NO_MOVES);
                break;
            }
        } while (!input.equals(EXIT));
    }


}

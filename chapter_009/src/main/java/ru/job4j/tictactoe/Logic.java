package ru.job4j.tictactoe;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Logic {
    private static final String CHOOSE_MOVE_AND_COLUMN = "Введите ваш ход" + System.lineSeparator() + "Введите номер столбца:";
    private static final String CHOOSE_ROW = "Введите номер строки:";
    private static final String NO_MOVES = "Ходов не осталось";
    private static final String CROSSES_IS_WIN = "Крестики победили";
    private static final String ZEROES_IS_WIN = "Нолики победили";
    private static final char CROSS = 'X';
    private static final char ZERO = 'O';


    private Field field;
    private final int winLine;
    private Scanner scanner;

    public Logic(Scanner scanner, Field field, final int winLine) {
        this.scanner = scanner;
        this.field = field;
        this.winLine = winLine;
    }

    public boolean isWin(char sign) {
        boolean result = field.isCharFillLine(sign, winLine, 0, 0, 1, 0)
                || field.isCharFillLine(sign, winLine, 0, 0, 0, 1)
                || field.isCharFillLine(sign, winLine, 0, 0, 1, 1)
                || field.isCharFillLine(sign, winLine, 1, 0, 0, 1)
                || field.isCharFillLine(sign, winLine, this.field.loadSize() - 1, 0, 0, 1)
                || field.isCharFillLine(sign, winLine, 0, 1, 1, 0)
                || field.isCharFillLine(sign, winLine, 0, this.field.loadSize() - 1, 1, 0)
                || field.isCharFillLine(sign, winLine, this.field.loadSize() - 1, 0, -1, 1);
        if (result) {
            if (sign == CROSS) {
                System.out.println(CROSSES_IS_WIN);
            } else if (sign == ZERO) {
                System.out.println(ZEROES_IS_WIN);
            }
        }
        return result;
    }


    public void computerMove() {
        if (!hasGap()) {
            System.out.println(NO_MOVES);
        } else {
            int column;
            int row;
            do {
                column = (int) (Math.random() * field.loadSize());
                row = (int) (Math.random() * field.loadSize());
            } while (field.isCellOccupied(column, row));
            setO(column, row);
            printField();
        }
    }

    public void userMove() {
        String column;
        String row;
        do {
            System.out.println(CHOOSE_MOVE_AND_COLUMN);
            column = this.scanner.nextLine();
            System.out.println(CHOOSE_ROW);
            row = this.scanner.nextLine();
        } while (!inputValidate(column, row));
        setX(Integer.parseInt(column), Integer.parseInt(row));
        printField();
    }

    public void move(int moveCounter) {
        if (moveCounter % 2 == 0) {
            computerMove();
        } else {
            userMove();
        }
    }

    public boolean hasWinner(int moveCounter) {
        return moveCounter % 2 == 0 ? isWin(CROSS) : isWin(ZERO);
    }


    public boolean hasGap() {
        boolean result = Stream.of(this.field.loadField())
                .flatMap(e -> IntStream.range(0, e.length).mapToObj(i -> e[i]))
                .anyMatch(e -> e == '.');
        if (!result) {
            System.out.println(NO_MOVES);
        }
        return result;
    }

    public void setX(int column, int row) {
        System.out.println("Игрок поставил нолик на:");
        System.out.println("столбец: " + column + "; " + "строка: " + row);
        this.field.writeSignToField(column, row, 'X');
    }

    public void setO(int column, int row) {
        System.out.println("Компьютер поставил нолик на:");
        System.out.println("столбец: " + column + "; " + "строка: " + row);
        this.field.writeSignToField(column, row, 'O');
    }

    public boolean inputValidate(String column, String row) {
        boolean result = false;
        int intColumn;
        int intRow;
        try {
            intColumn = Integer.parseInt(column);
            intRow = Integer.parseInt(row);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода, введите два целых числа.");
            return false;
        }
        if (intColumn > this.field.loadSize() - 1 || intRow > this.field.loadSize() - 1) {
            System.out.println("Слишком большие числа!");
            return false;
        }
        if (!this.field.isCellOccupied(intColumn, intRow)) {
            result = true;
        }
        return result;
    }

    public void printField() {
        this.field.show();
    }
}

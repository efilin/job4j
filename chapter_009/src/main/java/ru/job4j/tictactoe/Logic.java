package ru.job4j.tictactoe;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Logic {
    private Field field;
    private final int winLine;

    public Logic(Field field, final int winLine) {
        this.field = field;
        this.winLine = winLine;
    }

    public boolean isWin(char sign) {
        return field.isCharFillLine(sign, winLine, 0, 0, 1, 0)
                || field.isCharFillLine(sign, winLine, 0, 0, 0, 1)
                || field.isCharFillLine(sign, winLine, 0, 0, 1, 1)
                || field.isCharFillLine(sign, winLine, 1, 0, 0, 1)
                || field.isCharFillLine(sign, winLine, this.field.loadSize() - 1, 0, 0, 1)
                || field.isCharFillLine(sign, winLine, 0, 1, 1, 0)
                || field.isCharFillLine(sign, winLine, 0, this.field.loadSize() - 1, 1, 0)
                || field.isCharFillLine(sign, winLine, this.field.loadSize() - 1, 0, -1, 1);
    }

    public void computerMove() {
        int column;
        int row;
        do {
            column = (int) (Math.random() * field.loadSize());
            row = (int) (Math.random() * field.loadSize());
        } while (field.isCellOccupied(column, row));
        setO(column, row);
    }

    public boolean hasGap() {
        return Stream.of(this.field.loadField())
                .flatMap(e -> IntStream.range(0, e.length).mapToObj(i -> e[i]))
                .anyMatch(e -> e == '.');
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

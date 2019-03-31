package ru.job4j.tictactoe;

public class Field {
    private char[][] field;

    public Field(int size) {
        this.field = new char[size][size];
        init();
    }

    public void init() {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[0].length; j++) {
                this.field[i][j] = '.';
            }
        }
    }

    public void show() {
        for (int i = 0; i < this.field.length; i++) {
            System.out.println();
            for (int j = 0; j < this.field[0].length; j++) {
                System.out.print(this.field[i][j]);
            }
        }
        System.out.println();
    }

    public boolean isCharFillLine(char sign, int lineLength, int startColumn, int startRow, int deltaColumn, int deltaRow) {
        boolean result = true;
        for (int index = 0; index != this.field.length && index != lineLength; index++) {
            char cell = this.field[startRow][startColumn];
            startColumn += deltaColumn;
            startRow += deltaRow;
            if (cell != sign) {
                result = false;
                break;
            }
        }
        return result;
    }


    public void writeSignToField(int column, int row, char sign) {
        this.field[row][column] = sign;
    }

    public boolean isCellOccupied(int column, int row) {
        return this.field[row][column] != '.';
    }

    public int loadSize() {
        return this.field.length;
    }

    public char[][] loadField() {
        return this.field;
    }
}

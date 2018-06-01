package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {

    private final int [][] values;
    private int i = 0;
    private int j = 0;
    private  int counter = 0;

    public MatrixIterator(final int [][] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        return values.length > i && values[i].length > j;
    }

    @Override
    public Object next() {
        int result = 0;
        while (i < values.length && j < values[i].length) {
            if (i < values.length - 1 && j < values[i].length - 1) {
                result = values[i][j];
                j++;
            } else if (i < values.length - 1 && j == values[i].length - 1) {
                j = 0;
                result = values[i++][j];
            } else {
                result = values[i][j];
                break;
            }
        }
        return result;
    }
}

package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int i = 0;
    private int j = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        return values.length > i && values[i].length > j;
    }

    @Override
    public Object next() {
        int result;
        if (i < values.length && j < values[i].length - 1) {
            result = values[i][j++];
        } else if (i < values.length && j == values[i].length - 1) {
            result = values[i][j];
            j = 0;
            i++;
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}

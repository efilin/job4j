package ru.job4j.iterator;

import java.util.Iterator;

public class EvenIterator implements Iterator {

    private final int[] numbers;
    private int i = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {


        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}

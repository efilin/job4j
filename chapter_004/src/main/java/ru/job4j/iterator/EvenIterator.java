package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {

    private final int[] numbers;
    private int i = 0;
    private int index = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        for (index = i; index != numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int result = 0;
        if (hasNext()) {
            result = numbers[index];
            i = index + 1;
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}

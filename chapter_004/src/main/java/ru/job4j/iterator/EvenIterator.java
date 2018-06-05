package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {

    private final int[] numbers;
    int i = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = i; index != numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (i == numbers.length) {
            throw new NoSuchElementException();
        }
        int result = 0;
        for (int index = i; index != numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                result = numbers[index];
                i = index + 1;
                break;
            } else if (index == numbers.length - 1 && numbers[index] % 2 != 0) {
                throw new NoSuchElementException();
            }
        }
        return result;
    }
}

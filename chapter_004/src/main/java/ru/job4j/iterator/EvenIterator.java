package ru.job4j.iterator;

import java.util.Iterator;

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
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {

        return null;
    }
}

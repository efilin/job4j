package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {

    private final int[] numbers;
    private int counter = 0;
    private int index = 0;

    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    public boolean numberIsPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        for (index = counter; index != numbers.length; index++) {
            if (numberIsPrime(numbers[index])) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            counter = index + 1;
            return numbers[index];
        } else {
            throw new NoSuchElementException();
        }
    }
}

package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {

    private final int[] numbers;
    private int counter = 0;

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
        for (int index = counter; index != numbers.length; index++) {
            if (numberIsPrime(numbers[index])) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int result = 0;
        if (counter == numbers.length) {
            throw new NoSuchElementException();
        }
        for (int index = counter; index != numbers.length; index++) {
            if (numberIsPrime(numbers[index])) {
                counter = index + 1;
                result = numbers[index];
                break;
            } else if (index == numbers.length - 1 && !numberIsPrime(numbers[index])) {
                throw new NoSuchElementException();
            }
        }
        return result;
    }
}

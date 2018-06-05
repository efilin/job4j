package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> currentIterator = it.next();

            @Override
            public boolean hasNext() {
                boolean result = true;
                if (!currentIterator.hasNext()) {
                    result = false;
                    while (it.hasNext()) {
                        currentIterator = it.next();
                        if (currentIterator.hasNext()) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                Integer result = null;
                if (!currentIterator.hasNext() && !it.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentIterator.hasNext()) {
                    result = currentIterator.next();
                } else {
                    while (it.hasNext()) {
                        currentIterator = it.next();
                        if (currentIterator.hasNext()) {
                            result = currentIterator.next();
                            break;
                        }
                    }
                }
                return result;
            }
        };
    }
}
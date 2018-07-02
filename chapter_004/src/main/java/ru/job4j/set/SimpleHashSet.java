package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.Arrays;
import java.util.Objects;

public class SimpleHashSet<E> {
    private int arrayLength = 10;
    DynamicArray<E>[] simpleList = new DynamicArray[arrayLength];

    public SimpleHashSet() {
        for (int i = 0; i < arrayLength; i++) {
            simpleList[i] = new DynamicArray<>();
        }
    }

    boolean add(E e) {
        if (!contains(e)) {
            simpleList[hash(e)].add(e);
            return true;
        }
        return false;
    }


    boolean contains(E e) {
        return simpleList[hash(e)].contains(e);
    }


    boolean remove(E e) {
        if (!contains(e)) {
            return false;
        }
        simpleList[hash(e)].remove(hash(e));
        return true;
    }

    private int hash(E e) {
        int result = e.hashCode() % arrayLength;
        return result;
    }

}

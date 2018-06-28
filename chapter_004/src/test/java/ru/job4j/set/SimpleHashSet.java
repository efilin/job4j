package ru.job4j.set;

import ru.job4j.list.DynamicArray;

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
        return e.equals(simpleList[hash(e)]);
    }

    boolean remove(E e) {
        if (!contains(e)) {
            return false;
        }
        return false;
    }

    private int hash(E e) {
        return e.hashCode() % arrayLength;
    }


}

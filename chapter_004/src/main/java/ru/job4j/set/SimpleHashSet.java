package ru.job4j.set;


public class SimpleHashSet<E> {

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int arrayLength = 10;
    private Object[] values;
    private int count = 0;
    private float logFactor = 0;


    public SimpleHashSet() {
        this.values = new Object[arrayLength];
    }

    public boolean add(E e) {
        if (logFactor > DEFAULT_LOAD_FACTOR) {
            resize();
        }
        if (values[hash(e)] == null) {
            values[hash(e)] = e;
            logFactor = (float) ++count / arrayLength;
            return true;
        }
        return false;
    }


    boolean contains(E e) {
        return e.equals(values[hash(e)]);
    }

    boolean remove(E e) {
        if (values[hash(e)] != null) {
            values[hash(e)] = null;
            count--;
            return true;
        }
        return false;
    }

    private int hash(E e) {
        return e.hashCode() % arrayLength;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    private void resize() {
        arrayLength = arrayLength * 2;
        Object[] newValues = new Object[arrayLength];
        for (Object data : newValues) {
            if (data != null) {
                newValues[hash((E) data)] = data;
            }
        }
        values = newValues;
    }

}

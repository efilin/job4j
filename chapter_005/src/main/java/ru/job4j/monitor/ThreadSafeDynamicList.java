package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.LinkedListContainer;


import java.util.Iterator;

@ThreadSafe
public class ThreadSafeDynamicList<E> implements Iterable<E> {
    @GuardedBy("this")
    private LinkedListContainer<E> linkedListContainer = new LinkedListContainer<>();


    public synchronized void add(E date) {
        linkedListContainer.add(date);
    }

    public synchronized E delete() {
        return linkedListContainer.delete();
    }


    public synchronized E get(int index) {
        return linkedListContainer.get(index);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return linkedListContainer.iterator();
    }
}

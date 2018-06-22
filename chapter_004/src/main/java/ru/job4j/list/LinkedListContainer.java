package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<E> extends SimpleArrayList<E> implements Iterable<E> {

    protected int modCount = 0;
    private Node<E> last;


    @Override
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        if (size == 0) {
            first = newLink;
        } else {
            this.last.next = newLink;
        }
        this.last = newLink;
        this.size++;
        modCount++;
    }

    @Override
    public E delete() {
        modCount++;
        return super.delete();
    }

    @Override
    public E get(int index) {
        return super.get(index);
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private SimpleArrayList.Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                checkForComodification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = current.date;
                current = current.next;
                return result;
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}

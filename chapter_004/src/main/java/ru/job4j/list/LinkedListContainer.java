package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<E> extends SimpleArrayList<E> implements Iterable<E> {

    private int modCount = 0;
    Node<E> last;


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


    private void unlink(int index) {
        final Node<E> next = this.getNode(index).next;

        if (index == 0) {
            first = this.getNode(index).next;
        } else {
            this.getNode(index - 1).next = this.getNode(index).next;
        }

        if (next == null) {
            last = this.getNode(index - 1);
        } else {
            this.getNode(index).next = null;
        }
        size--;
        modCount++;
    }


    @Override
    public E get(int index) {
        return super.get(index);
    }

    Node<E> getNode(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private SimpleArrayList.Node<E> current = first;
            private SimpleArrayList.Node<E> lastReturned;
            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                checkForComodification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                current = current.next;
                nextIndex++;
                return lastReturned.date;
            }

            public void remove() {
                checkForComodification();
                int index = 0;
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                unlink(nextIndex - 1);
                nextIndex--;
                lastReturned = null;
                expectedModCount++;
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}

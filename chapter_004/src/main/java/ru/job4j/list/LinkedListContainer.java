package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<E> extends SimpleArrayList<E> implements Iterable<E> {

    protected int modCount = 0;
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


    @Override
    public E get(int index) {
        return super.get(index);
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
                Node<E> temp = first;
                if (nextIndex == 1) {
                    first = first.next;
                    size--;
                    nextIndex--;
                    return;
                }
                while (temp.next != null) {
                    if (index == nextIndex - 1) {
                        temp.next = temp.next.next;
                        size--;
                        nextIndex--;
                        return;
                    } else {
                        index++;
                        if (index == size - 1) {
                            last = temp;
                            temp.next = null;
                            size--;
                            nextIndex--;
                            return;
                        }
                        temp = temp.next;
                    }
                }
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

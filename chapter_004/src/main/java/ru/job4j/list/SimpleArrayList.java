package ru.job4j.list;


/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> {

    int size;
    Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        Node<E> del = this.first;
        this.first = del.next;
        this.size--;
        return del.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Метод, определяющий, что список содержит замыкания.
     */
    public static boolean hasCycle(Node first) {
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    public static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}

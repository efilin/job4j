package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    public Tree(E e) {
        this.root = new Node<>(e);
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(child) == null) {
            return false;
        }
        findBy(parent).get().add(new Node<>(child));
        return true;
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            int counter = 0;
            for (Node<E> child : el.leaves()) {
                data.offer(child);
                counter++;
                if (counter > 2) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<Node<E>> data = new LinkedList<>();
            boolean r = data.offer(Tree.this.root);


            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = data.poll();
                for (Node<E> element : result.leaves()) {
                    data.offer(element);
                }
                return result.getValue();
            }
        };
    }
}

package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ConcurrentModificationException;

/* Необходимо создать связный список */
public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private int modCount;
    private Node<E> head;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = this.last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            this.head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        Node<E> current = head;
        int count = 0;

        while (count != index) {
            current = current.next;
            count++;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private int expectedModCount = modCount;
            private int count = 0;
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> res = current;
                current = current.next;
                return res.item;
            }
        };
    } /* End of Iterator */
}
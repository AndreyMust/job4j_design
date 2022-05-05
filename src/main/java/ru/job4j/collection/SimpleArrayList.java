package ru.job4j.collection;

import ru.job4j.list.List;
import java.util.*;

/*
    создадим реализацию списка на основе динамического массива, аналог ArrayList.
 */
public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void upSize() {
        container =  Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            upSize();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T old = get(index);
        container[index] = newValue;
        modCount++;
        return old;
    }


    @Override
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        container[size] = null;
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }

        };
    }
}

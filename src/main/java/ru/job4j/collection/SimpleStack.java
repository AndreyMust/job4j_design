package ru.job4j.collection;

/* The type SimpleStack - класс реализация структуры хранения Stack. */
/* на базе односвязного списка.*/
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();
    private int size = 0;

    /* Pop - метод для извлечения из начала списка.*/
    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    /* Push - метод для добавления в начало  списка. */
    public void push(final T value) {
        linked.addFirst(value);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

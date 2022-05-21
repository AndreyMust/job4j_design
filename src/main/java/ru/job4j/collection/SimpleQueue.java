package ru.job4j.collection;

import java.util.NoSuchElementException;

/* класс SimpleQueue - реализация очереди(FIFO) на базе двух  стеков */
public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /* Метод должен возвращать первое значение и удалять его из коллекции. */
    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /*Метод push - помещает значение в конец.*/
    public void push(final T value) {
        in.push(value);
    }
}

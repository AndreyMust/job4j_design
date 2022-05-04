package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = index < data.length && data[index] % 2 == 0;
        while (!res && index < data.length - 1) {
            index++;
            res = data[index] % 2 == 0;
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    public static void main(String[] args) {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {2, 3, 4, 6});
        while (it.hasNext()) {
            int el = it.next();
            System.out.println(el);
        }
    }
}
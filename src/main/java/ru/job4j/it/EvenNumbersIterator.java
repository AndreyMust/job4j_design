package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        findNext();
    }

    @Override
    public boolean hasNext() {
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data[index];
        index++;
        findNext();
        return result;
    }

    private void findNext() {
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                return;
            }
            index++;
        }
    }

    public static void main(String[] args) {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {2, 3, 4, 6});
        while (it.hasNext()) {
            int el = it.next();
            System.out.println(el);
        }
    }
}
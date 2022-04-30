package ru.job4j.it;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    ArrayList<Integer> arrayList = new ArrayList<>();
    private int pointer = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        for (int[] row: data) {
            for (int el: row) {
                arrayList.add(el);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return (pointer < arrayList.size());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(pointer++);
    }
}

package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row + 1 < data.length
                && column == data[row].length) {
            column = 0;
            row++;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

    public static void main(String[] args) {
        int[][] inData = {{1}, {}, {2, 3}};
        MatrixIt it = new MatrixIt(inData);
        System.out.println("#1 " + it.next());
        System.out.println("#2 " + it.next());
        System.out.println("#3 " + it.next());
    }
}

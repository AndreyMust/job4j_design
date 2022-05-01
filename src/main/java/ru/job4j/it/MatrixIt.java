package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        if (data[0].length == 0) {
            moveNotEmptyRow();
        }
    }

    private void moveNotEmptyRow() {
        row++;
        while (row < data.length) {
            if (data[row].length > 0) {
                return;
            }
            row++;
        }
    }

    private void moveNext() {
        if (column + 1 < data[row].length) {
            column++;
        } else {
            moveNotEmptyRow();
            column = 0;
        }
    }

    @Override
    public boolean hasNext() {
        if (row >= data.length) {
            return false;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data[row][column];
        moveNext();
        return result;
    }

    public static void main(String[] args) {
        int[][] inData = {{1}, {}, {2, 3}};
        MatrixIt it = new MatrixIt(inData);
        System.out.println("#1 " + it.next());
        System.out.println("#2 " + it.next());
        System.out.println("#3 " + it.next());
    }
}

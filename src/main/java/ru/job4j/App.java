package ru.job4j;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ArrayList<Integer> arrayList = new ArrayList<>();
        int[][] in = {{1}, {2, 3}};

        for (int[] row: in) {
            for (int el: row) {
                System.out.println(row.length);
                System.out.println(el);
                arrayList.add(el);
            }
        }
        System.out.println(arrayList);
    }
}

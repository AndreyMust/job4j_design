package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/* Вывод таблицы умножения в файл*/
public class ResultFile {

    public static void main(String[] args) {
        System.out.println("Enter the number:");
        Scanner input = new Scanner(System.in);
        int size = Integer.parseInt(input.nextLine());
        try (FileOutputStream out = new FileOutputStream("./data/result.txt")) {
            for (int row = 1; row <= size; row++) {
                for (int col = 1; col <= size; col++) {
                    int mult = row * col;
                    String space = mult < 10 ? "  " : " ";
                    out.write((mult + space).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

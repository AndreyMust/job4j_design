package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    /*Метод filter должен прочитать файл и вернуть строки, где ПРЕДпоследнее значение - равно 404.*/
    public List<String> filter(String file) {
        List<String> log = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            log = in.lines()
                    .filter(e -> "404".equals(e.split(" ")[e.split(" ").length - 2]))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    /*Запись в файл списка данных*/
    public static void save(List<String> log, String file) {
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(printWriter::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save2(List<String> log, String file) {
        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            BufferedOutputStream bufferedStream = new BufferedOutputStream(fileStream);
            PrintWriter printWriter = new PrintWriter(bufferedStream);

            log.forEach(printWriter::println);

            printWriter.close(); /* Без этого не происходит записи в файл*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("./data/log.txt");
        save2(log, "./data/404.txt");
    }
}

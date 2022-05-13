package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("./data/log.txt");
        log.forEach(System.out::println);
    }
}

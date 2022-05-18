package ru.job4j.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Analizy {

    public static void writeFile(String target, List<String> lines) {
        try {
            Files.write(Path.of(target), lines, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String source) {
        List<String> result = null;
        try {
            result = Files.readAllLines(Paths.get(source), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean is400or500(String inStr) {
        String[] split = inStr.split(" ");
        return ("500").equals(split[0]) || ("400").equals(split[0]);
    }

    private static boolean is200or300(String inStr) {
        String[] split = inStr.split(" ");
        return ("200").equals(split[0]) || ("300").equals(split[0]);
    }

    private static String getTime(String str) {
        return str.split(" ")[1];
    }

    public static void unavailable(String source, String target) {
        List<String> inLines = readFile(source);
        List<String> outLines = new ArrayList<>();
        AtomicReference<String> start = new AtomicReference<>("");
        String res = inLines.stream()
                .reduce((x, y) -> {
                    if ((is400or500(y)) && (is200or300(x))) {
                        start.set(y);
                    }
                    if ((is200or300(y)) && (is400or500(x))) {
                        outLines.add(getTime(start.get()) + ";" + getTime(y));
                    }
                    return y;
                })
                .orElse("");
        System.out.println(outLines);
        writeFile(target, outLines);
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/unavailable.txt");
    }
}

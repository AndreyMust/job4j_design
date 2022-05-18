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
        return split[0].equals("500") || split[0].equals("400");
    }

    private static boolean is200or300(String inStr) {
        String[] split = inStr.split(" ");
        return split[0].equals("200") || split[0].equals("300");
    }

    public static void unavailable(String source, String target) {
        List<String> inLines;
        List<String> outLines = new ArrayList<>();
        try {
            inLines = Files.readAllLines(Paths.get(source), StandardCharsets.US_ASCII);

            String startTime = "";
            for (String line : inLines) {
                String[] split = line.split(" ");
                if (is400or500(line)) {
                    if ("".equals(startTime)) {
                        startTime = split[1];
                    }
                } else if ((startTime.length() > 0) && (is200or300(line))) {
                    outLines.add(startTime + ";" + split[1]);
                    startTime = "";
                }
            }

            writeFile(target, outLines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTime(String str) {
        return str.split(" ")[1];
    }

    public static void unavailable1(String source, String target) {
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
                .orElse(null);
        System.out.println(outLines);
        writeFile(target, outLines);
    }

    public static void main(String[] args) {
        unavailable1("./data/server.log", "./data/unavailable.txt");

    }
}

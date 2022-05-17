package ru.job4j.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public static void writeFile(String target, List<String> lines) {
        try {
            Files.write(Path.of(target), lines, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unavailable(String source, String target) {
        List<String> inLines;
        List<String> outLines = new ArrayList<>();
        try {
            inLines = Files.readAllLines(Paths.get(source), StandardCharsets.UTF_8);

            String start = "";
            for (String line: inLines) {
                String[] split = line.split(" ");
                if (split[0].equals("500") || split[0].equals("400")) {
                    if ("".equals(start)) {
                        start = split[1];
                    }
                } else if ((start.length() > 0) && (split[0].equals("200") || split[0].equals("300"))) {
                    outLines.add(start + ";" + split[1]);
                    System.out.println(start + ";" + split[1]);
                    start = "";
                }
            }

            writeFile(target, outLines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/unavailable.txt");
    }
}

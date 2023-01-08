package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

        Path path = Paths.get(argsName.get("path"));
        Path target = Paths.get(argsName.get("out"));
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");

        List<String> filterList = new ArrayList<>();
        try (var filterScanner = new Scanner(filter).useDelimiter(",")) {
            while (filterScanner.hasNext()) {
                filterList.add(filterScanner.next());
            }
        }

        List<String[]> lines = Files.lines(Paths.get(String.valueOf(path)))
                .map(l -> l.split(";"))
                .collect(toList());

        String[] title = lines.get(0);
        List<Integer> filterIndex = new ArrayList<>();

        for (String columnName:filterList) {
            for (int i = 0; i < title.length; i++) {
                if (columnName.equals(title[i])) {
                    filterIndex.add(i);
                }
            }
        }

        StringBuilder rsl = new StringBuilder();
        for (String[] line : lines) {
            List<String> strings = new ArrayList<>();
            for (int idx : filterIndex) {
                strings.add(line[idx]);
            }
            rsl.append(String.join(";", strings)).append(System.lineSeparator());
        }

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target.toFile()))) {
            out.write(rsl.toString().getBytes());
        }

    }

    private static String[] validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder is null");
        }
        Path start = Paths.get(ArgsName.of(args).get("path"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (!start.toFile().isFile()) {
            throw new IllegalArgumentException(String.format("Not file %s", start.toFile().getAbsoluteFile()));
        }
        return args;
    }

    public static void main(String[] args) throws Exception {
        String[] arg = validate(args);
        ArgsName argsName = ArgsName.of(arg);
        handle(argsName);
    }

}

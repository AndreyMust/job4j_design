package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    /*Сканирование файловой системы с предикатом*/
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar target/search.jar . java");
        }
        Path start = Paths.get(args[0]);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException("Paths not exist " + start);
        }
    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Paths.get(args[0]);
        String strEnd = args[1];
        search(start, p -> p.toFile().getName().endsWith(strEnd)).forEach(System.out::println);
    }
}

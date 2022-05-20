package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    /* Поиск Дубликатов фалов*/
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor samples = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), samples);
        samples.getDuplicates().forEach(System.out::println);
    }
}
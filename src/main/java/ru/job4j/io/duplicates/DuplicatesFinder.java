package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    /* build with Maven: mvn package */
    /* Run: java -jar target/duplicatesFinder.jar */

    /* Поиск Дубликатов фалов */
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor samples = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), samples);
        samples.getDuplicates().forEach(System.out::println);
    }
}
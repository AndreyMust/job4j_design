package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* Строит Мапу для поиска Дубликатов фалов по названию и размеру*/
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> filesMap = new HashMap<>();

    public List<Path> getDuplicates() {
        List<Path> duplicatesList = new ArrayList<>();
        filesMap.values()
                .stream()
                .filter(paths -> paths.size() > 1)
                .forEach(duplicatesList::addAll);
        return duplicatesList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());

        filesMap.putIfAbsent(fileProperty, new ArrayList<>());
        filesMap.get(fileProperty).add(file);

        return super.visitFile(file, attrs);
    }
}
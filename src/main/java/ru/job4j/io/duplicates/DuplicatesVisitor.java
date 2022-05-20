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

        List<Path> pathList = filesMap.containsKey(fileProperty) ? filesMap.get(fileProperty) : new ArrayList<>();
        pathList.add(file);
        filesMap.put(fileProperty, pathList);

        return super.visitFile(file, attrs);
    }
}
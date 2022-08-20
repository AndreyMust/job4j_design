package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validation(String[] args) throws IllegalArgumentException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Указаны не все аргументы");
        }
        ArgsName argsName = ArgsName.of(args);

        if (argsName.get("d") == null) {
            throw new IllegalArgumentException("Аргумент -d не найден");
        }
        if (argsName.get("e") == null) {
            throw new IllegalArgumentException("Аргумент -e не найден");
        }
        if (argsName.get("o") == null) {
            throw new IllegalArgumentException("Аргумент -o не найден");
        }
        File fileFolder = new File(argsName.get("d"));
        if (!fileFolder.isDirectory() || !fileFolder.exists()) {
            throw  new IllegalArgumentException("Директория для архивации не найдена");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        validation(args);

        ArgsName argsName = ArgsName.of(args);
        /* -d - directory - которую мы хотим архивировать. */
        Path mainSource = Paths.get(argsName.get("d"));
        /* -e - exclude - исключить файлы с расширением  */
        String exclude = argsName.get("e");
        List<Path> sources = Search.search(mainSource, p -> !p.toFile().getName().endsWith(exclude));
        /* -o - output - выходной файл */
        /* Пример строки:    -d=C:\Projects_Java\job4j_design -e=.class -o=project.zip */
        packFiles(sources, new File(argsName.get("o")));

    }
}

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

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );


        ArgsName argsName = ArgsName.of(args);
        if (args.length < 3) {
            throw new IllegalArgumentException("Указаны не все аргументы");
        }
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

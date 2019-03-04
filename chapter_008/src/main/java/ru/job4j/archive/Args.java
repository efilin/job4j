package ru.job4j.archive;

       /* 1. Задана директория проекта, например: c:\project\job4j\
        2. В качестве ключей передаются расширения файлов, которые должны попасть в архив.
        3. Архив должен сохранять структуру проекта.
        4. Запуск проекта java -jar pack.jar -d c:\project\job4j\ -e java.xml -o project.zip
        java -jar pack.jar - Это собранный jar.
        -d - directory - которую мы ходим архивировать
        -e - exclude - исключить файлы *.xml
        -o - output - во что мы архивируем.
        У вас должен быть класс new Args(args)
        с методами directory() exclude() output();
        5. Для архивации использовать классы https://docs.oracle.com/javase/7/docs/api/java/util/zip/ZipOutputStream.html*/

//Убрал сборку в .jar, для работы над заданием finder.FileFinder

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Args {
    private String directory;
    private String ext;
    private String filename;

    private Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[i + 1];
            } else if (args[i].equals("-e")) {
                ext = args[i + 1];
            } else if (args[i].equals("-o")) {
                filename = args[i + 1];
            }
        }
    }


    private void pack() throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filename))) {
            Path pp = Paths.get(directory);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .filter(path -> !path.toFile().getName().endsWith("." + ext))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zos.putNextEntry(zipEntry);
                            Files.copy(path, zos);
                            zos.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }


    public static void main(String[] args) throws IOException {
        Args args1 = new Args(args);
        args1.pack();
    }
}

package ru.job4j.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private boolean isRecursive = false;
    private List<File> result;

    /*
     *
     * Файловая система представляет собой древовидную структуру.
     * В модуле "Коллекции Про" рассматривался алгоритм обхода дерева.
     * Этот алгоритм обхода в ширину.
     * В этом задании нужно написать метод, который возвращает список всех файлов с конкретным расширением.
     * Метод должен заходить во всех каталоги.
     * Для этого нужно использовать алгоритм обхода дерева в ширину.
     * String parent - это путь до каталога, с которого нужно осуществлять поиск.
     * List<String> exts - это расширения файлов, которые мы ходим получить.
     * В этой задаче нужно использовать методы.
     * File file = new File(path);
     * file.listFiles() - возвращает список всех каталогов и файлов находящихся в папке  file.
     * file.isDirectory() - проверяет, что файл является директорией.
     * file.getName() - возвращает имя файла с расширением.
     * В этой задаче нужно написать тесты. Для тестов нужно создать временную структуру с файлами.
     * Структуру нужно создавать в папке System.getProperty("java.io.tmpdir")
     * Здесь нельзя использовать FileVisitor. Это задание на работу с деревом каталогов.
     *
     * */

    public List<File> files(String parent, List<String> exts) {
        if (!this.isRecursive) {
            this.result = new ArrayList<>();
        }
        File file = new File(parent);
        var filesAndDirectories = file.listFiles();
        if (filesAndDirectories != null) {
            for (File fd : filesAndDirectories) {
                if (fd.isDirectory()) {
                    this.isRecursive = true;
                    files(fd.getPath(), exts);
                } else if (checkExt(fd, exts)) {
                    this.result.add(fd);
                }
            }
        }
        return this.result;
    }

    private boolean checkExt(File file, List<String> exts) {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        return exts.contains(extension);
    }

}

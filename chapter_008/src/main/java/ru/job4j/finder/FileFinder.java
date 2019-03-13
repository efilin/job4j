package ru.job4j.finder;

        /*1. Создать программу для поиска файла.
        2. Программа должна искать данные в заданном каталоге и подкаталогах.
        3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
        4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
        Ключи
        -d - директория в которая начинать поиск.
        -n - имя файл, маска, либо регулярное выражение.
        -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.
        -o - результат записать в файл.
        5. Программа должна записывать результат в файл.
        6. В программе должна быть валидация ключей и подсказка.*/


import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder {

    private List<Path> fileList;
    private Args args;
    private String directory;
    private String inputFilename;
    private String mask;
    private String regExp;
    private String outputFilename;

    public FileFinder(String[] args) {
        this.args = new Args(args);
    }

    public void init() {
        this.directory = args.getDirectory();
        this.inputFilename = args.getInputFilename();
        this.mask = args.getMask();
        this.regExp = args.getRegExp();
        this.outputFilename = args.getOutputFilename();
    }

    public static void main(String[] args) throws IOException {
        FileFinder finder = new FileFinder(args);
        finder.init();
        finder.find();
        finder.writeToFile();
    }


    //Поиск и запись найденных файлов в List
    public void find() throws IOException {
        if (this.inputFilename != null) {
            this.fileList = findByName(this.inputFilename);
        } else if (this.mask != null) {
            this.fileList = findByMask(this.mask);
        } else if (this.regExp != null) {
            this.fileList = findByRegExp(this.regExp);
        }

    }

    private List<Path> findByName(String filename) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.directory))) {
            return filesStream
                    .filter(f -> f.toFile().getName().equals(filename))
                    .collect(Collectors.toList());
        }
    }

    private List<Path> findByMask(String mask) throws IOException {
        FileFilter fileFilter = new WildcardFileFilter(mask);
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.directory))) {
            return filesStream
                    .map(Path::toFile)
                    .filter(fileFilter::accept)
                    .map(File::toPath)
                    .collect(Collectors.toList());
        }
    }

    private List<Path> findByRegExp(String regExp) throws IOException {
        FileFilter fileFilter = new RegexFileFilter(regExp);
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.directory))) {
            return filesStream
                    .map(Path::toFile)
                    .filter(fileFilter::accept)
                    .map(File::toPath)
                    .collect(Collectors.toList());
        }
    }

    //Запись Файлов из List в Файл
    public void writeToFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(outputFilename)))) {
            this.fileList.forEach(pw::println);
        }
    }
}

package ru.job4j.gc;

// Консольная програма
// Считывает файлы из системы и выдает содержимое файлов, сначала пробует из кэша,
// если там нет загружает в кэш и берет оттуда
// Текстовые файл должны лежать в одной директории.
// Пример. Names.txt, Address.txt - файлы в системе.
// При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.

import com.google.common.base.Joiner;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SoftCache {

    private Scanner scanner;
    private SoftHashMap<String, String> softMap = new SoftHashMap<>();

    private static final String LN = System.lineSeparator();

    private static final String START_MESSAGE = Joiner.on(LN)
            .join("Программа с Кэшем на SoftReference",
                    "Программа считывает данные из кэша по имени файла",
                    "если данных в кэше нет, записывает в кэш из файла",
                    "Для выхода из программы введите exit");
    private static final String ENTER_FILENAME = "Введите имя файла:";
    private static final String EXIT = "exit";
    private final String directory;

    public SoftCache(String directory, Scanner scanner) {
        this.directory = directory;
        this.scanner = scanner;
    }

    public void show() throws IOException {
        System.out.println(START_MESSAGE);
        String input;
        String output;
        do {
            System.out.println(ENTER_FILENAME);
            input = scanner.nextLine();
            output = this.softMap.get(input);
            if (output != null) {
                System.out.println(output);
            } else {
                loadToCache(input);
                System.out.println(this.softMap.get(input));
            }
        } while (!input.equalsIgnoreCase(EXIT));
    }

    public void loadToCache(String filename) throws IOException {
        File file = new File(this.directory + "/" + filename);
        if (file.exists()) {
            try (BufferedReader bw = new BufferedReader(new FileReader(file))) {
                String value = bw.lines().collect(Collectors.joining());
                this.softMap.put(filename, value);
            }
        } else {
            System.out.println("Файл не найден, введите правильное имя файла.");
        }
    }

    public static void main(String[] args) throws IOException {
        SoftCache cache = new SoftCache(System.getProperty("java.io.tmpdir"), new Scanner(System.in));
        cache.show();
    }
}

package ru.job4j.sqlruparser;

import java.time.LocalDateTime;

public class Vacancy {
    private int id;
    private String name;
    private String text;
    private String url;
    private LocalDateTime date;

    public Vacancy(int id, String name, String text, String url, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.url = url;
        this.date = date;
    }
}

package ru.job4j.sqlruparser;

import java.time.LocalDateTime;

public class Vacancy {
    private String name;
    private String text;
    private String url;
    private LocalDateTime date;

    public Vacancy(String name, String text, String url, LocalDateTime date) {
        this.name = name;
        this.text = text;
        this.url = url;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

package ru.job4j.sqlruparser;

public class Vacancy {
    private int id;
    private String name;
    private String text;
    private String url;

    public Vacancy(int id, String name, String text, String url) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

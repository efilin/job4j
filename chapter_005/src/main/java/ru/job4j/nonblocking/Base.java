package ru.job4j.nonblocking;

public class Base {

    private int id;
    private int version;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.version = 0;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.version++;
    }
}

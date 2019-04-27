package ru.job4j.crud;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private String createDate;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate='" + createDate + '\''
                + '}';
    }
}

package ru.job4j.crud;

public interface Validate {
    String add(User user);

    String update(int id, User user);

    String delete(int id);

    String findAll();
}

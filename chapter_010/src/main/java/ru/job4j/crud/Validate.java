package ru.job4j.crud;

import java.util.List;

public interface Validate {
    String add(User user);

    String update(int id, User user);

    String delete(int id);

    List<User> findAll();

    User findById(int id);
}

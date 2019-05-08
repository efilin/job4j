package ru.job4j.crud;

import java.util.List;

public interface Store {

    boolean add(User user);

    void update(int id, User user);

    boolean delete(int id);

    List<User> findAll();

    User findById(int id);
}

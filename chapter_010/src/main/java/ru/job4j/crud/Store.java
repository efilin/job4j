package ru.job4j.crud;

import java.util.List;

public interface Store {

    boolean add(User user);

    void update(int id, User user);

    boolean delete(int id);

    List<User> findAll();

    User findById(int id);

    boolean isCredential(String name, String password);

    String userRole(String name, String password);

    List<String> getCities(String country);
}

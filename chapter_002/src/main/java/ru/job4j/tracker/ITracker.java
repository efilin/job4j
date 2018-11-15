package ru.job4j.tracker;

import java.util.List;

public interface ITracker {
    Item add(Item item);
    void replace(int id, Item item);
    void delete(int id);
    List<Item> findAll();
    List<Item> findByName(String name);
    Item findById(int id);
}

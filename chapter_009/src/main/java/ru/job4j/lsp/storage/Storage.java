package ru.job4j.lsp.storage;


import java.util.Set;

public interface Storage {

    boolean add(Food food);
    Set<Food> getStorage();
}

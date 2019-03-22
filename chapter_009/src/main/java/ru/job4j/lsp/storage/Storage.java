package ru.job4j.lsp.storage;


import java.util.List;

public interface Storage {

    boolean isAppropriate(Food food);

    boolean add(Food food);

    List<Food> getStorage();
}

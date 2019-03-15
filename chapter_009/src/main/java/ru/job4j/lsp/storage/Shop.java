package ru.job4j.lsp.storage;

import java.util.Set;

public class Shop implements Storage {

    private Set<Food> storage;

    public Shop(Set<Food> storage) {
        this.storage = storage;
    }


    @Override
    public boolean add(Food food) {
        return this.storage.add(food);
    }

    @Override
    public Set<Food> getStorage() {
        return this.storage;
    }
}

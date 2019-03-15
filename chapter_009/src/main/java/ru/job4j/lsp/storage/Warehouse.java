package ru.job4j.lsp.storage;

import java.util.Set;

public class Warehouse implements Storage {

    private Set<Food> storage;

    public Warehouse(Set<Food> storage) {
        this.storage = storage;
    }

    @Override
    public boolean add(Food food) {
        return this.storage.add(food);
    }

    public Set<Food> getStorage() {
        return this.storage;
    }
}

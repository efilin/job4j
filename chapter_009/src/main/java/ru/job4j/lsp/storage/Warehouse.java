package ru.job4j.lsp.storage;

import java.util.List;

public class Warehouse implements Storage {

    private static final int STORAGE_SIZE = 5;

    private List<Food> storage;

    public Warehouse(List<Food> storage) {
        this.storage = storage;
    }

    public boolean isFull() {
        return this.storage.size() > STORAGE_SIZE;
    }

    @Override
    public boolean isAppropriate(Food food) {
        return food.getExpirePercent() <= 25 && !isFull();
    }

    @Override
    public boolean add(Food food) {
        return this.storage.add(food);
    }

    @Override
    public void clearStorage() {
        this.storage.clear();
    }


    @Override
    public List<Food> getStorage() {
        return this.storage;
    }

}

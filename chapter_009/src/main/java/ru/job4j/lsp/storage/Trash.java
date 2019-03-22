package ru.job4j.lsp.storage;

import java.util.List;

public class Trash implements Storage {

    private List<Food> storage;

    public Trash(List<Food> storage) {
        this.storage = storage;
    }

    @Override
    public boolean isAppropriate(Food food) {
        return food.getExpirePercent() > 100;
    }

    @Override
    public boolean add(Food food) {
        return this.storage.add(food);
    }

    @Override
    public List<Food> getStorage() {
        return this.storage;
    }
}
package ru.job4j.lsp.storage;

import java.util.List;

public class Warehouse implements Storage {

    private List<Food> storage;

    public Warehouse(List<Food> storage) {
        this.storage = storage;
    }

    @Override
    public boolean isAppropriate(Food food) {
        return food.getExpirePercent() <= 25;
    }

    @Override
    public boolean add(Food food) {
        return this.storage.add(food);
    }

    @Override
    public List getStorage() {
        return this.storage;
    }

}

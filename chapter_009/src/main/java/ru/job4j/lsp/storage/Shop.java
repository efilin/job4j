package ru.job4j.lsp.storage;

import java.util.List;

public class Shop implements Storage {

    private List<Food> storage;

    public Shop(List<Food> storage) {
        this.storage = storage;
    }

    @Override
    public boolean isAppropriate(Food food) {
        return food.getExpirePercent() > 25 && food.getExpirePercent() < 100;
    }

    @Override
    public boolean add(Food food) {
        if (food.getExpirePercent() >= 75) {
            food.setDiscount(25);
        }
        return this.storage.add(food);
    }

    @Override
    public List getStorage() {
        return this.storage;
    }
}

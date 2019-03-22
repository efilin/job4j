package ru.job4j.lsp.storage;

import java.util.List;

public class BaseDecorator implements Storage {

    private Storage storage;

    public BaseDecorator(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean isAppropriate(Food food) {
        return this.storage.isAppropriate(food);
    }

    @Override
    public boolean add(Food food) {
        return this.storage.add(food);
    }

    @Override
    public List<Food> getStorage() {
        return this.storage.getStorage();
    }
}

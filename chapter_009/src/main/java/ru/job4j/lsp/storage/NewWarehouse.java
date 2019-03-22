package ru.job4j.lsp.storage;

import java.util.List;

public class NewWarehouse extends BaseDecorator {

    private List<Food> storage;

    public NewWarehouse(Storage storage) {
        super(storage);
        this.storage = storage.getStorage();
    }


    @Override
    public boolean isAppropriate(Food food) {
        return super.isAppropriate(food);
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

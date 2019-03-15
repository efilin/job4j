package ru.job4j.lsp.storage;


public class ControlQuality {
    private Storage storage;


    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public boolean addToStorage(Food food) {
        return this.storage.add(food);
    }

}

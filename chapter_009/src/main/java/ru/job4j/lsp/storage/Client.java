package ru.job4j.lsp.storage;

import java.util.HashSet;

public class Client {
    private Storage shop = new Shop(new HashSet<>());
    private Storage warehouse = new Warehouse(new HashSet<>());
    private Storage trash = new Trash(new HashSet<>());
    private ControlQuality cq = new ControlQuality();

    public void addFoodToStorages(Food food) {
        if (food.getExpirePercent() <= 25) {
            cq.setStorage(warehouse);
            cq.addToStorage(food);
        } else if (food.getExpirePercent() > 25 && food.getExpirePercent() < 75) {
            cq.setStorage(shop);
            cq.addToStorage(food);
        } else if (food.getExpirePercent() >= 75 && food.getExpirePercent() < 100) {
            cq.setStorage(shop);
            food.setDiscount(25);
            cq.addToStorage(food);
        } else {
            cq.setStorage(trash);
            cq.addToStorage(food);
        }
    }

    public Storage getShop() {
        return shop;
    }

    public Storage getWarehouse() {
        return warehouse;
    }

    public Storage getTrash() {
        return trash;
    }

}

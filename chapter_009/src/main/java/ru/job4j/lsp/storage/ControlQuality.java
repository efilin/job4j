package ru.job4j.lsp.storage;


import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Storage> storageList;

    public ControlQuality() {
        setListOfStorage();
    }

    public void setListOfStorage() {
        this.storageList = new ArrayList<>();
        this.storageList.add(new Warehouse(new ArrayList<>()));
        this.storageList.add(new Shop(new ArrayList<>()));
        this.storageList.add(new Trash(new ArrayList<>()));
    }


    public void add(Food food) {
        for (Storage storage : this.storageList) {
            if (storage.isAppropriate(food)) {
                storage.add(food);
            }
        }
    }

    public List<Storage> getStorageList() {
        return storageList;
    }
}

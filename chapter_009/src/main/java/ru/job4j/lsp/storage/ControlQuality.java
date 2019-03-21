package ru.job4j.lsp.storage;


import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Storage> store = new ArrayList<>();

    public ControlQuality() {
        init();
    }

    public void init() {
        this.store.add(new Warehouse(new ArrayList<>()));
        this.store.add(new Shop(new ArrayList<>()));
        this.store.add(new Trash(new ArrayList<>()));
    }


    public void add(Food food) {
        for (Storage storage : this.store) {
            if (storage.isAppropriate(food)) {
                storage.add(food);
            }
        }
    }

    public List<Storage> load() {
        return store;
    }
}

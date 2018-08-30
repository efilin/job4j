package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private ConcurrentHashMap<Integer, Base> data =  new ConcurrentHashMap<>();

    public void add(Base model) {
        data.put(model.getId(),model);
    }

    public void update(Base model) {
        data.replace(model.getId(), model);
    }

    public void delete(Base model) {
        data.remove(model.getId());
    }
}

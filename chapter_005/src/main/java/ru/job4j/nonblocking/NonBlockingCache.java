package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class NonBlockingCache {

    private ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return this.data.putIfAbsent(model.getId(), model) == null;
    }

    public void update(Base model) {
        this.data.computeIfPresent(model.getId(), (key, value) -> {
            if (value.getVersion() == model.getVersion()) {
                model.setName(model.getName());
                return model;
            } else {
                throw new OptimisticException("Текущая версия не совпадает с обновляемой, в обновлении отказано!");
            }
        });
    }

    public void delete(Base model) {
        this.data.remove(model.getId());
    }
}

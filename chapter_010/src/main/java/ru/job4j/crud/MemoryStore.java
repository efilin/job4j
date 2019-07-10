package ru.job4j.crud;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public final class MemoryStore implements Store {

    private static final Store INSTANCE = new MemoryStore();
    private static final Random RN = new Random();
    private final List<User> users = new CopyOnWriteArrayList<>();

    private MemoryStore() {
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        user.setId(generateId());
        return this.users.add(user);
    }

    @Override
    public void update(int id, User user) {
        user.setId(id);
        delete(id);
        this.users.add(user);
    }

    @Override
    public boolean delete(int id) {
        return this.users.removeIf(user -> user.getId() == id);
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User findById(int id) {
        return this.users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public boolean isCredential(String name, String password) {
        boolean exists = false;
        for (User user: this.users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public String userRole(String name, String password) {
        return null;
    }

    @Override
    public List<String> getCities(String country) {
        return null;
    }

    private int generateId() {
        return RN.nextInt();
    }
}

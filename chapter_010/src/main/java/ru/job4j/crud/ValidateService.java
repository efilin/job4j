package ru.job4j.crud;

import java.util.List;

public class ValidateService implements Validate {

    private final Store logic = DbStore.getInstance();

    private static final Validate INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public String add(User user) {
        String result;
        if (this.logic.add(user)) {
            result = "User has been added";
        } else {
            result = "User has not been added";
        }
        return result;
    }

    @Override
    public String update(int id, User user) {
        String result;
        if (this.logic.findById(id) != null) {
            this.logic.update(id, user);
            result = "User has been updated";
        } else {
            result = "User has not been founded";
        }
        return result;
    }

    @Override
    public String delete(int id) {
        String result;
        if (this.logic.delete(id)) {
            result = "User has been deleted";
        } else {
            result = "User has not been deleted";
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return this.logic.findAll();
    }

    @Override
    public User findById(int id) {
        return this.logic.findById(id);
    }
}

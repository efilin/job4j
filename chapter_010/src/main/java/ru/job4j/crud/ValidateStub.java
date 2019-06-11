package ru.job4j.crud;

import java.util.List;

public class ValidateStub implements Validate {

    private final Store logic = MemoryStore.getInstance();


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
        return null;
    }

    @Override
    public boolean isCredential(String name, String password) {
        return false;
    }

    @Override
    public String userRole(String name, String password) {
        return null;
    }
}

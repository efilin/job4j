package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage;

    public UserStorage() {
        storage = new ArrayList<>();
    }

    public synchronized boolean add(User user) {
        return storage.add(user);
    }


    public synchronized boolean update(User user) {
        for (User user1 : storage) {
            if (user.getId() == user1.getId()) {
                user1.setAmount(user.getAmount());
                return true;
            }
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        for (User user1 : storage) {
            if (user.getId() == user1.getId()) {
                return storage.remove(user);
            }
        }
        return false;
    }

    private synchronized User findUserById(int id) throws NullPointerException {
        for (User user1 : storage) {
            if (user1.getId() == id) {
                return user1;
            }
        }
        return null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User sender = findUserById(fromId);
        User recipient = findUserById(toId);
        sender.setAmount(sender.getAmount() - amount);
        recipient.setAmount(recipient.getAmount() + amount);
    }
}

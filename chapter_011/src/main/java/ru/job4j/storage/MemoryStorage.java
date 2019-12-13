package ru.job4j.storage;

import org.springframework.stereotype.Component;
import ru.job4j.beanexamples.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryStorage implements Storage {

    private List<User> userList = new ArrayList<>();

    @Override
    public void add(User user) {
        this.userList.add(user);
    }
}

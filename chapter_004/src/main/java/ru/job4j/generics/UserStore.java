package ru.job4j.generics;

public class UserStore extends AbstractStore<User> {

    public UserStore(int count) {
        setStore(new User[count]);
        setPosition(0);
    }
}

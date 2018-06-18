package ru.job4j.generics;

public class UserStore extends AbstractStore<User> {

    public UserStore(int count) {
        this.store = new User[count];
        this.position = 0;
    }
}

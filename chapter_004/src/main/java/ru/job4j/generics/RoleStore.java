package ru.job4j.generics;

public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int count) {
        setStore(new Role[count]);
        setPosition(0);
    }
}

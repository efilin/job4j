package ru.job4j.generics;

public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int count) {
        this.store = new Role[count];
        this.position = 0;
    }
}

package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddUserAndFindItById() {
        RoleStore roleStore = new RoleStore(10);
        roleStore.add(new Role("4"));
        Role expected = new Role("7");
        roleStore.add(expected);
        Role result = roleStore.findById("7");
        assertThat(result, is(expected));
    }

    @Test
    public void whenReplaceUserAndFindItById() {
        RoleStore roleStore = new RoleStore(10);
        roleStore.add(new Role("4"));
        roleStore.add(new Role("6"));
        Role expected = new Role("9");
        roleStore.replace("4", expected);
        Role result = roleStore.findById("9");
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUserAndResultIsTrue() {
        RoleStore roleStore = new RoleStore(10);
        roleStore.add(new Role("2"));
        roleStore.add(new Role("5"));
        assertThat(roleStore.delete("5"), is(true));
    }
}
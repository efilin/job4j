package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {


    @Test
    public void whenAddUserAndFindItById() {
        UserStore userStore = new UserStore(10);
        userStore.add(new User("4"));
        User expected = new User("7");
        userStore.add(expected);
        User result = userStore.findById("7");
        assertThat(result, is(expected));
    }

    @Test
    public void whenReplaceUserAndFindItById() {
        UserStore userStore = new UserStore(10);
        userStore.add(new User("4"));
        userStore.add(new User("6"));
        User expected = new User("9");
        userStore.replace("4", expected);
        User result = userStore.findById("9");
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUserAndResultIsTrue() {
        UserStore userStore = new UserStore(10);
        userStore.add(new User("2"));
        userStore.add(new User("5"));
        assertThat(userStore.delete("5"), is(true));
    }

}
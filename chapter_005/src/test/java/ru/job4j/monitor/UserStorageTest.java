package ru.job4j.monitor;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransferMoney() {
        UserStorage store = new UserStorage();
        User userOne = new User(1, 100);
        User userTwo = new User(2, 200);

        store.add(userOne);
        store.add(userTwo);

        store.transfer(1, 2, 50);
        assertThat(userOne.getAmount(), is(50));
        assertThat(userTwo.getAmount(), is(250));
    }
}
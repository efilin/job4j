package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void firstTest() {
        User userOne = new User("Mike", 2, new GregorianCalendar(1980, 10, 21));
        User userTwo = new User("Mike", 2, new GregorianCalendar(1980, 10, 21));
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(userOne, "first");
        userMap.put(userTwo, "second");
        System.out.println(userMap);
    }
}
package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserConvertTest {
    @Test
    public void whenThreeUsersConvertsIntoMap() {
        UserConvert userConvert = new UserConvert();
        User userOne = new User(1, "Alex", "Moscow");
        User userTwo = new User(2, "Roman", "Pskov");
        User userThree = new User(3, "Nikita", "Dmitrov");
        List<User> users = List.of(
                userOne,
                userTwo,
                userThree
        );
        Map<Integer, User> result = userConvert.process(users);
        Map<Integer, User> expect = Map.of(
                1, userOne,
                2, userTwo,
                3, userThree
        );
       assertThat(result, is(expect));

    }

}

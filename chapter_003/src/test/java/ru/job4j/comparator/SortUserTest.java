package ru.job4j.comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUserTest {

    @Test
    public void whenSortByAge() {

        SortUser.User userOne = new SortUser.User("Billy", 26);
        SortUser.User userTwo = new SortUser.User("Willy", 19);
        SortUser.User userThree = new SortUser.User("Dilly", 34);
        Set<SortUser.User> result = new TreeSet<>();
        result.add(userOne);
        result.add(userTwo);
        result.add(userThree);
        List<SortUser.User> expect = new ArrayList<>();
        expect.add(userTwo);
        expect.add(userOne);
        expect.add(userThree);
        assertThat(result.toArray(), is(expect.toArray()));
    }
}

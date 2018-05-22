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

    @Test
    public void whenSortNameLenght() {
        SortUser.User userOne = new SortUser.User("William", 26);
        SortUser.User userTwo = new SortUser.User("Aik", 19);
        SortUser.User userThree = new SortUser.User("Mike", 34);
        List<SortUser.User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);
        List<SortUser.User> expect = new ArrayList<>();
        expect.add(userTwo);
        expect.add(userThree);
        expect.add(userOne);
        List<SortUser.User> result = new SortUser().sortNameLength(list);
        assertThat(result, is(expect));
    }


    @Test
    public void whenSortByNameAndAge() {
        SortUser.User userOne = new SortUser.User("Сергей", 25);
        SortUser.User userTwo = new SortUser.User("Иван", 30);
        SortUser.User userThree = new SortUser.User("Сергей", 20);
        SortUser.User userFour = new SortUser.User("Иван", 25);
        List<SortUser.User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);
        list.add(userFour);
        List<SortUser.User> expect = new ArrayList<>();
        expect.add(userFour);
        expect.add(userTwo);
        expect.add(userThree);
        expect.add(userOne);
        List<SortUser.User> result = new SortUser().sortByAllFields(list);
        assertThat(result, is(expect));
    }
}

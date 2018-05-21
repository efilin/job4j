package ru.job4j.comparator;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.list.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUserTest {

    @Test
    public void whenSortByAge() {
        List <SortUser.User> list = new ArrayList<>();
        list.add(new SortUser.User("Billy", 26));
        list.add(new SortUser.User("Willy", 19));
        list.add(new SortUser.User("Dilly", 34));
        Set<SortUser.User> result = new TreeSet<>();
        result.add(new SortUser.User("Willy", 19));
        result.add(new SortUser.User("Billy", 26));
        result.add(new SortUser.User("Dilly", 34));
        Assert.assertThat(list.sort();)
    }
}

package ru.job4j.userstore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreTest {
    private List<Store.User> previous = new ArrayList<>();
    private List<Store.User> current = new ArrayList<>();


    @Test
    public void whenDiffIsTwoAddTwoChangedOneDeleted() {

        previous.add(new Store.User(13, "Nikky"));
        previous.add(new Store.User(16, "Peter"));
        previous.add(new Store.User(21, "Mike"));
        previous.add(new Store.User(91, "Joe"));
        previous.add(new Store.User(120, "Alex"));

        current.add(new Store.User(13, "Nikolas"));
        current.add(new Store.User(19, "Steve"));
        current.add(new Store.User(21, "Mikel"));
        current.add(new Store.User(91, "Joe"));
        current.add(new Store.User(120, "Alex"));
        current.add(new Store.User(125, "Gary"));

        Info result = new Store().diff(previous, current);
        Info expected = new Info(2, 2, 1);
        assertThat(result, is(expected));
    }
}
package ru.job4j.cinema;

import java.util.List;

public interface Store {
    boolean add(Account account, int seat);
    List<Boolean> getSeats();
}

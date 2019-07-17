package ru.job4j.cinema;

import java.util.List;

public interface Validate {
    boolean add(Account account, int seat);
    List<Integer> getOccupiedSeats();
}

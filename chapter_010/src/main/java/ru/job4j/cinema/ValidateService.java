package ru.job4j.cinema;

import java.util.List;

public class ValidateService implements Validate {

    private final Store logic = DbStore.getInstance();
    private static final Validate INSTANCE = new ValidateService();

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Account account, int seat) {
        return this.logic.add(account, seat);
    }

    @Override
    public List<Integer> getOccupiedSeats() {
        return this.logic.getOccupiedSeats();
    }
}

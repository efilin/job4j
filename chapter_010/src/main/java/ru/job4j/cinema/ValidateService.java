package ru.job4j.cinema;

public class ValidateService implements Validate {

    private final Store logic = DbStore.getInstance();
    private static final Validate INSTANCE = new ValidateService();

    public static Validate getInstance() {
        return INSTANCE;
    }

}

package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Store INSTANCE = new DbStore();


    public static Store getInstance() {
        return INSTANCE;
    }

}

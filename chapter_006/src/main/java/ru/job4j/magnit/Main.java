package ru.job4j.magnit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        StoreSQL sql = new StoreSQL(new Config());

    }


}

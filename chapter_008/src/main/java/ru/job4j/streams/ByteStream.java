package ru.job4j.streams;

import java.io.*;

public class ByteStream {

    public boolean isEven(InputStream in) {
        try (Reader reader = new InputStreamReader(in)) {
            int element;
            while ((element = reader.read()) != -1) {
                if (element % 2 == 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

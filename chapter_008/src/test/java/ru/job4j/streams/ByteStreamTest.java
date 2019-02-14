package ru.job4j.streams;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ByteStreamTest {

    @Test
    public void whenArrayHasNoEven() {
        var byteStream = new ByteStream();
        byte[] array = {3, 5, 7, 9};
        var inputStream = new ByteArrayInputStream(array);
        var result = byteStream.isEven(inputStream);
        assertFalse(result);
    }

    @Test
    public void whenArrayHasEven() {
        var byteStream = new ByteStream();
        byte[] array = {3, 5, 8, 9};
        var inputStream = new ByteArrayInputStream(array);
        var result = byteStream.isEven(inputStream);
        assertTrue(result);
    }
}
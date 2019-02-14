package ru.job4j.streams;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ForbiddenWordsFilterTest {

    @Test
    public void whenInputContainAbuseWords() {
        var filter = new ForbiddenWordsFilter();
        var inputString = "Mama she has taught me well Told me when I was young Son your life's an open book Don't close it 'fore its done";
        String[] array = {"Son", "taught", "well"};
        InputStream in = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        filter.dropAbuses(in, out, array);
        String result = out.toString();
        String expected = "Mama she has  me  Told me when I was young  your life's an open book Don't close it 'fore its done";
        assertThat(result, is(expected));
    }

    @Test
    public void whenInputNotContainAbuseWords() {
        var filter = new ForbiddenWordsFilter();
        var inputString = "The brightest flame burns quickest Is what I heard her say A son's heart's owed to mother But I must find my way";
        String[] array = {"Son", "taught", "well"};
        InputStream in = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        filter.dropAbuses(in, out, array);
        String result = out.toString();
        String expected = "The brightest flame burns quickest Is what I heard her say A son's heart's owed to mother But I must find my way";
        assertThat(result, is(expected));
    }
}
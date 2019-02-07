package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConverterTest {

    @Test
    public void whenConvertMatrixToList() {
        Converter converter = new Converter();
        Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        List<Integer> result = converter.convertMatrixToList(matrix);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        assertThat(result, is(expected));
    }
}
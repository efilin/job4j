package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DepartmentsSortTest {

    DepartmentsSort departmentsSort = new DepartmentsSort();
    String[] departments = new String[7];

    @Before
    public void setUp() {
        departments[0] = "K1\\SK1";
        departments[1] = "K1\\SK2";
        departments[2] = "K1\\SK1\\SSK1";
        departments[3] = "K1\\SK1\\SSK2";
        departments[4] = "K2";
        departments[5] = "K2\\SK1\\SSK1";
        departments[6] = "K2\\SK1\\SSK2";
    }

    @Test
    public void whenAscendingSort() {
        String[] result = departmentsSort.ascendingSort(departments);
        String[] expected = new String[9];
        expected[0] = "K1";
        expected[1] = "K1\\SK1";
        expected[2] = "K1\\SK1\\SSK1";
        expected[3] = "K1\\SK1\\SSK2";
        expected[4] = "K1\\SK2";
        expected[5] = "K2";
        expected[6] = "K2\\SK1";
        expected[7] = "K2\\SK1\\SSK1";
        expected[8] = "K2\\SK1\\SSK2";
        assertThat(result, is(expected));
    }

    @Test
    public void whenDescendingSort() {
        String[] result = departmentsSort.descendingSort(departments);
        String[] expected = new String[9];
        expected[0] = "K2";
        expected[1] = "K2\\SK1";
        expected[2] = "K2\\SK1\\SSK2";
        expected[3] = "K2\\SK1\\SSK1";
        expected[4] = "K1";
        expected[5] = "K1\\SK2";
        expected[6] = "K1\\SK1";
        expected[7] = "K1\\SK1\\SSK2";
        expected[8] = "K1\\SK1\\SSK1";
        assertThat(result, is(expected));
    }
}
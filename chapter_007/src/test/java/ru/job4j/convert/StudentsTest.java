package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StudentsTest {

    @Test
    public void whenConvertToMap() {
        Students students = new Students();
        List<Student> studentList = List.of(
                new Student(80, "Ivanov"),
                new Student(74, "Petrov"),
                new Student(90, "Sidorov"),
                new Student(80, "Ivanov")
        );
        Map<String, Student> result = students.convertToMap(studentList);
        Map<String, Student> expected = Map.of(
                "Ivanov", new Student(80, "Ivanov"),
                "Petrov", new Student(74, "Petrov"),
                "Sidorov", new Student(90, "Sidorov")
        );

        assertEquals(expected, result);
    }
}
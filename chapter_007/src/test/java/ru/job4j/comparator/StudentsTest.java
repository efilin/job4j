package ru.job4j.comparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StudentsTest {

    @Test
    public void whenSortStudentsByScore() {
        Students students = new Students();
        List<Student> studentList = List.of(
                new Student(30, "Ivanov"),
                new Student(70, "Petrov"),
                new Student(36, "Sidorov"),
                new Student(),
                new Student(60, "Smith"),
                new Student(80, "Dow")
        );
        List<Student> result = students.levelOf(studentList, 60);
        List<Student> expected = List.of(
                new Student(80, "Dow"),
                new Student(70, "Petrov")

        );
        assertThat(result, is(expected));
    }
}
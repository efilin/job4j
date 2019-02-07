package ru.job4j.filter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchoolTest {

    @Test
    public void whenScoreAboveSeventy() {
        School school = new School();
        List<Student> students = List.of(
                new Student(75),
                new Student(67),
                new Student(55),
                new Student(32),
                new Student(93)
        );
        List<Student> expected = List.of(
                new Student(75),
                new Student(93)
        );
        List<Student> aClassStudents = school.collect(students, s -> s.getScore() >= 70);
        assertEquals(aClassStudents, expected);
    }

    @Test
    public void whenScoreBetweenFiftyAndSeventy() {
        School school = new School();
        List<Student> students = List.of(
                new Student(75),
                new Student(67),
                new Student(55),
                new Student(32),
                new Student(93)
        );
        List<Student> expected = List.of(
                new Student(67),
                new Student(55)
        );
        List<Student> bClassStudents = school.collect(students, s -> (s.getScore() < 70 && s.getScore() >= 50));
        assertEquals(bClassStudents, expected);
    }

    @Test
    public void whenScoreBelowFifty() {
        School school = new School();
        List<Student> students = List.of(
                new Student(75),
                new Student(67),
                new Student(55),
                new Student(32),
                new Student(93)
        );
        List<Student> expected = List.of(
                new Student(32)
        );
        List<Student> cClassStudents = school.collect(students, s -> s.getScore() < 50);
        assertEquals(cClassStudents, expected);
    }

}
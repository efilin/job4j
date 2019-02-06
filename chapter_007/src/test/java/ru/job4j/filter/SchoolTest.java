package ru.job4j.filter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchoolTest {

    @Test
    public void whenScoreAboveSeventy() {
        School school = new School();
        List<Student> students = new ArrayList<>();
        students.add(new Student(75));
        students.add(new Student(67));
        students.add(new Student(55));
        students.add(new Student(32));
        students.add(new Student(93));
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(75));
        expected.add(new Student(93));
        List<Student> aClassStudents = school.collect(students, s -> s.getScore() >= 70);
        assertEquals(aClassStudents, expected);
    }

    @Test
    public void whenScoreBetweenFiftyAndSeventy() {
        School school = new School();
        List<Student> students = new ArrayList<>();
        students.add(new Student(75));
        students.add(new Student(67));
        students.add(new Student(55));
        students.add(new Student(32));
        students.add(new Student(93));
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(67));
        expected.add(new Student(55));
        List<Student> bClassStudents = school.collect(students, s -> (s.getScore() < 70 && s.getScore() >= 50));
        assertEquals(bClassStudents, expected);
    }

    @Test
    public void whenScoreBelowFifty() {
        School school = new School();
        List<Student> students = new ArrayList<>();
        students.add(new Student(75));
        students.add(new Student(67));
        students.add(new Student(55));
        students.add(new Student(32));
        students.add(new Student(93));
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(32));
        List<Student> cClassStudents = school.collect(students, s -> s.getScore() < 50);
        assertEquals(cClassStudents, expected);
    }

}
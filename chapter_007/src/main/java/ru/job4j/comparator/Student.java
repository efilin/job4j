package ru.job4j.comparator;

import java.util.Comparator;

public class Student implements Comparator<Student> {
    private int score;
    private String name;

    public Student() {
    }

    public Student(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }

        Student student = (Student) o;

        if (score != student.score) {
            return false;
        }
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

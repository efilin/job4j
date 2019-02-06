package ru.job4j.convert;

import java.util.Objects;

public class Student {
    private int score;
    private String lastName;

    public Student(int score, String lastName) {
        this.score = score;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score
                && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, lastName);
    }
}

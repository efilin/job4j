package ru.job4j.comparator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Students {
    public List<Student> levelOf(List<Student> students, int bound) {
        return students
                .stream()
                .sorted((o1, o2) -> o2.getScore() - o1.getScore())
                .flatMap(Stream::ofNullable)
                .takeWhile(e -> e.getScore() > bound)
                .collect(Collectors.toList());
    }
}

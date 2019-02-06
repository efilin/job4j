package ru.job4j.convert;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Students {
    public Map<String, Student> convertToMap(List<Student> list) {
        return list.stream()
                .sorted(Comparator.comparing(Student::getLastName))
                .distinct()
                .collect(Collectors.toMap(
                        Student::getLastName,
                        e -> e));
    }
}

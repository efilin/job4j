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
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(80, "Ivanov"));
        studentList.add(new Student(74, "Petrov"));
        studentList.add(new Student(90, "Sidorov"));
        studentList.add(new Student(80, "Ivanov"));
        Map<String, Student> result = students.convertToMap(studentList);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Ivanov", new Student(80, "Ivanov"));
        expected.put("Petrov", new Student(74, "Petrov"));
        expected.put("Sidorov", new Student(90, "Sidorov"));
        assertEquals(expected, result);
    }
}
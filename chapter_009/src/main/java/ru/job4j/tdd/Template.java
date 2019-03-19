package ru.job4j.tdd;

import java.util.Map;

public interface Template {
    /**
     * Hello, ${name}
     * @param template
     * @param data
     * @return
     */
    String generate(String template, Map<String, String> data);
}

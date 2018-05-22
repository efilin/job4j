package ru.job4j.comparator;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    static class User implements Comparable<User> {

        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(User o) {
            return this.age - o.age;
        }

    }

    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>(list);
        return result;

    }
}

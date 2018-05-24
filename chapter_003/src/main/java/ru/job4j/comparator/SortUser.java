package ru.job4j.comparator;


import java.util.*;

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
        return new TreeSet<>(list);

    }

    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.length() - o2.name.length();
                }
            });
        return list;
    }


    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.name.compareTo(o2.name);
                result = result != 0 ? result : Integer.compare(o1.age, o2.age);
                return result;
            }
        });
        return list;
    }
}
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
        Set<User> result = new TreeSet<>(list);
        return result;

    }

    public List<User> sortNameLength(List<User> list) {
        Comparator<User> userComparator = new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.name.length() - o2.name.length();
                    }
                };
        Collections.sort(list, userComparator);
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> userComparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.name.compareTo(o2.name);
                if (result == 0) {
                    result = o1.age - o2.age;
                }
                return result;
            }
        };
        Collections.sort(list, userComparator);
        return list;
    }




}

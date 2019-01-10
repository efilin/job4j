package ru.job4j.comparator;


import java.util.*;
import java.util.stream.Collectors;


/**
 * В классе SortUser написать метод public Set<User> sort (List<User>),
 * который будет возвращать TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
 *
 * В классе SortUser из 1-го задания необходимо написать два метода:
 *
 * 1) public List<User> sortNameLength (List<User>) - в этом методе необходимо определить
 * Comparator для метода Collections.sort и отсортировать List<User> по длине имени.
 *
 *
 * 2) public List<User> sortByAllFields (List<User>) - в этом методе необходимо определить
 * Comparator для метода Collections.sort и отсортировать List<User> по обоим полям, сначала сортировка
 * по имени в лексикографическом порядке, потом по возрасту.
 *
 * Изменено на Stream API
 *
 */

public class SortUser {

    static class User implements Comparable<User> {

        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
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
        list.sort(Comparator.comparingInt(o -> o.name.length()));
        return list;
    }


    public List<User> sortByAllFields(List<User> list) {
        /*list.sort((o1, o2) -> {
            int result = o1.name.compareTo(o2.name);
            result = result != 0 ? result : Integer.compare(o1.age, o2.age);
            return result;
        });*/
        return list.stream()
                .sorted(Comparator.comparing(User::getName)
                        .thenComparing(User::getAge))
                .collect(Collectors.toList());
    }
}
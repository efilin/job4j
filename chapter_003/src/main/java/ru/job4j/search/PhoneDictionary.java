package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * Изменено на Stream API
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        /* List<Person> result = new ArrayList<>();
        for (Person person: this.persons) {
            if (person.getName().contains(key) || person.getSurname().contains(key) || person.getAddress().contains(key) || person.getAddress().contains(key) || person.getPhone().contains(key)) {
                result.add(person);
            }
        }*/
         return persons.stream()
                 .filter(person -> person.getName().contains(key)
                 || person.getSurname().contains(key)
                 || person.getAddress().contains(key)
                 || person.getPhone().contains(key))
                 .collect(Collectors.toList());
    }
}
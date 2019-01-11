package ru.job4j.tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class Tracker implements ITracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод, реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод, реализующий поиск заявки по id в хранилище
     * Изменено на Stream API
     *
     * @param id id заявки
     */
    public Item findById(int id) {
        /*Item result = null;
        for (Item item: items) {
            if (item != null && item.getId() == (id)) {
                result = item;
                break;
            }
        } return result;*/
        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst().orElse(null);
    }

    /**
     * Метод, выводящий список всех заявок
     *
     * @return Список всех заявок
     */
    public List<Item> findAll() {
        return this.items;
    }

    public void replace(int id, Item item) {
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index).getId() == (id)) {
                this.items.add(item);
                this.items.set(index, item);
                break;
            }
        }
    }

    public void delete(int id) {
        for (Item item : items) {
            if (item.getId() == (id)) {
                items.remove(item);
                break;
            }
        }
    }

    /**
     * Метод, реализующий поиск списка заявок по имени
     * Изменено на Stream API
     *
     * @param name имя заявки
     */
    public List<Item> findByName(String name) {
        /*List<Item> result = new ArrayList<>();
        for (Item item:items) {
            if (item != null && item.getName().equals(name))  {
                result.add(item);
            }
        } return result;*/
        return items.stream()
                .filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());
    }


    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private int generateId() {
        return RN.nextInt();
    }
}

package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class Tracker implements ITracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }
    public Item findById(String id) {
        Item result = null;
        for (Item item: items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        } return result;
    }
    public List<Item> findAll() {
        return this.items;
    }

    public  void replace(String id, Item item) {
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                this.items.add(item);
                this.items.set(index, item);
            }
            break;
        }
    }

    public void delete(String id) {
        for (Item item: items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                break;
            }
        }
    }

    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();

        for (Item item:items) {
            if (item != null && item.getName().equals(key))  {
                result.add(item);

            }
        } return result;
    }


    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}

package ru.job4j.tracker;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

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
        this.items[this.position++] = item;
        return item;
    }
    public Item findById(String id) {
        Item result = null;
        for (Item item: items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }return result;
    }
    public Item[] findAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    public  void replace(String id, Item item) {
        for (int index = 0; index != position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
            } break;
        }

        // редактирование заявок - public void replace(String id, Item item);
        // должен заменить ячейку в массиве this.items. Для этого необходимо найти ячейку в массиве по id
    }

    public void delete(String id) {
        for (int index = 0; index != position; index++) {
            if (this.items[index].getId().equals(id) ) {
                System.arraycopy(this.items, index, this.items, index - 1, this.items.length - index - 1);
            }
        }
       // должен удалить ячейку в массиве this.items. Для этого необходимо найти ячейку в массиве по id.
       // Далее сместить все значения справа от удаляемого элемента - на одну ячейку влево с помощью System.arrayCopy();
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int index = 0;
        for (Item item:items) {
            if (item.getName().equals(key))  {
                result[index] = item;
                index++;
            }
        }return Arrays.copyOf(result, result.length - index);
        // проверяет в цикле все элементы массива this.items, сравнивая name (используя метод getName класса Item)
        // с аргументом метода String key. Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его;
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

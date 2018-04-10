package ru.job4j.array;

import java.util.Arrays;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int k = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    String temp = array[j];
                    array[j] = array[array.length - k];
                    array[array.length - k] = temp;
                    k++;
                }
            }
        } return Arrays.copyOf(array, array.length - k + 1);
        //Перед обрезкой массива необходимо сгруппировать дубликаты в конце массива с помощью перестановок.
        //Метод должен убрать все дубликаты строк из массива;
        //Для обрезания массива надо использовать Arrays.copyOf метод;
    }
}

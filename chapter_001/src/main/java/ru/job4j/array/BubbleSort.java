package ru.job4j.array;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class BubbleSort {
    public int[] sort(int[] array) {
        for (int j = 1; j < array.length; j++) {
            for (int i = 0; i < array.length - j; i++) {
                int temp;
                if (array[i] > array[i + 1]) {
                    temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
        } return array;
    }
}

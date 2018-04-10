package ru.job4j.array;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Turn {
    public int[] turn(int[] array) {
        for (int i = 0; i < (array.length) / 2 ; i++) {
            int temp = array[i];
            array[i] = array[(array.length - 1) - i];
            array[(array.length - 1) - i] = temp;
        }return array;
    }
}

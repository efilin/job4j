package ru.job4j.array;

import java.util.Arrays;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int k = 0;
        for (int i = 0; i < array.length - k; i++) {
            for (int j = i + 1; j < array.length - k; j++) {
                if (array[i].equals(array[j])) {
                    if (array[j] != array[array.length - k - 1]) {
                        String temp = array[j];
                        array[j] = array[array.length - k - 1];
                        array[array.length - k - 1] = temp;
                    }
                    k++;
                }
            }
        } return Arrays.copyOf(array, array.length - k);
    }
}

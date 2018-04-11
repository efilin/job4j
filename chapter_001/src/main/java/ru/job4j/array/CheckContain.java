package ru.job4j.array;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class CheckContain {

    public boolean contains(String origin, String sub) {
        boolean result = false;
        char[] value = sub.toCharArray();
        char[] data = origin.toCharArray();
        for (int i = 0; i < (data.length - value.length - 1); i++) {
            for (int j = 0; j < (value.length - 1);) {
                if (data[i + j] == value[j]) {
                    if (j == value.length - 1) {
                        result = true;
                    }
                    j++;
                }
            }
        } return result;
    }
}



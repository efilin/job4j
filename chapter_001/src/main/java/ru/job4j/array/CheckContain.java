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

        for (int i = 0; i <= (data.length - value.length); i++) {
            int k = 0;
            for (int j = 0; j < value.length; j++) {
                if (data[i + j] == value[j]) {
                    k++;
                    if (k == value.length) {
                        result = true;
                        break;
                    }
                }
            }
        } return result;
    }
}



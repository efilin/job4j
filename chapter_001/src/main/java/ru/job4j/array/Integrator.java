package ru.job4j.array;

import java.util.Arrays;
/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

// Имеется два отсортированных целочисленных массива, требуется объединить их в третий массив.



public class Integrator {

    public int[] sort(int[] arrayOne, int[] arrayTwo) {
        int[] arrayThree = new int[arrayOne.length + arrayTwo.length];
        int i = 0;
        int j = 0;
        while (i != arrayOne.length  && j != arrayTwo.length) {
            arrayThree[i + j] = arrayOne[i] < arrayTwo[j] ? arrayOne[i++] : arrayTwo[j++];
        }
        if (i == arrayOne.length) {
            System.arraycopy(arrayTwo, j, arrayThree, i + j, arrayTwo.length - j);
        } else {
            System.arraycopy(arrayOne, i, arrayThree, i + j, arrayOne.length - i);
        }
        return arrayThree;
    }
}

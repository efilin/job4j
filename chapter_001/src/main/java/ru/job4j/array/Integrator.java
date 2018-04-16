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
        for (int i = 0; i < arrayThree.length; i++) {
            if (i < arrayOne.length) {
                arrayThree[i] = arrayOne[i];
            } else {
                arrayThree[i] = arrayTwo[i - arrayOne.length];
            }
        }
        //сортировка только для половины массива
        for (int j = 1; j <= arrayOne.length; j++) {
            for (int i = 0; i < arrayThree.length - j; i++) {
                int temp;
                if (arrayThree[i] > arrayThree[i + 1]) {
                    temp = arrayThree[i + 1];
                    arrayThree[i + 1] = arrayThree[i];
                    arrayThree[i] = temp;
                }
            }
        }
        return arrayThree;
    }
}
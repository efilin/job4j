package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * public List<Integer> toList (int[][] array) {} - в метод приходит двумерный массив целых чисел,
 * необходимо пройтись по всем элементам массива и добавить их в List<Integer>.
 *
 * Изменено на Stream API
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        /* List<Integer> list = new ArrayList<>();
        for (int[] cells: array) {
            for (int cell: cells) {
                list.add(cell);
            }
        }*/
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
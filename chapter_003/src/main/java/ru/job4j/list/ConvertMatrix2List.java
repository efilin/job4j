package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * public List<Integer> toList (int[][] array) {} - в метод приходит двумерный массив целых чисел,
 * необходимо пройтись по всем элементам массива и добавить их в List<Integer>.
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] cells: array) {
            for (int cell: cells) {
                list.add(cell);
            }
        } return list;
    }
}
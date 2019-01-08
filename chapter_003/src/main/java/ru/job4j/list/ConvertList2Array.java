package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * public int[][] toArray (List<Integer> list, int rows) {} - метод toArray должен равномерно разбить лист на количество
 * строк двумерного массива. В методе toArray должна быть проверка - если количество элементов не кратно количеству
 * строк - оставшиеся значения в массиве заполнять нулями.
 *
 * Например в результате конвертации листа со значениями (1,2,3,4,5,6,7)
 * с разбиением на 3 строки должен получиться двумерный массив {{1, 2, 3} {4, 5, 6} {7, 0 ,0}}
 *
 * public List<Integer> convert (List<int[]> list) *
 * В этом методе вы должны пройтись по всем элементам всех массивов в списке list и добавить их в один общий лист Integer.
 */

public class ConvertList2Array {

    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows + ((list.size() % rows == 0) ? 0 : 1);
        int[][] array = new int[rows][cells];
        int row = 0;
        int cell = 0;
        for (int i: list) {
            array[row][cell++] = i;
            if (cell == cells) {
                row++;
                cell = 0;
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> resultList = new ArrayList<>();
        for (int[] cells: list) {
            for (int cell: cells) {
                resultList.add(cell);

            }
        }
        return resultList;
    }
}
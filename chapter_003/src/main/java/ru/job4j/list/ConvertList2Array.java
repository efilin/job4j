package ru.job4j.list;

import java.util.List;

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
}
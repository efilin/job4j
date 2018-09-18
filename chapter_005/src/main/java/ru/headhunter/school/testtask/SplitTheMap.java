package ru.headhunter.school.testtask;

import java.util.ArrayList;
import java.util.List;

public class SplitTheMap {

    char[][] table;
    private int vacNumber;

    public static void main(String[] args) {
        /**
         *
         *   Получаем таблицу
         * v Вычисляем N
         *   Пытаемся разбить таблицу на N равных частей с учетом приоритета разбиений
         *
         */
    }

    private int getN(char[][] table) {
        int counter = 0;
        for (char[] aTable : table)
            for (char anATable : aTable) {
                if (anATable == 'o') {
                    counter++;
                }
            }
        return counter;
    }


    /**
     * Находим N
     * Вычисляем S(прямоугольной части)  = Общее количество элементов/N
     * Находим различные варианты прямоугольников при этой площади (S = 10 ->  10x1; 5x2; 2x5; 1x10)
     * Убираем варианты которые не помещаются равномерно в общую площадь
     *
     * @param table
     * @return
     */
    private ArrayList<char[][]> parseTable(char[][] table) {
        vacNumber = getN(table);
        // Проверка что общее количество элементов делится нацело на кол-во элементов
        if (((table[0].length * table.length) % vacNumber) != 0) {
            return null;
        }
        int Square = (table[0].length * table.length) / vacNumber;

        // Перебор вариантов разбиения на прямоугольники при этой площади
        for (int i = 1; i < Square; i++) {
            if (Square % i == 0) {
                int j = Square/i;
                if (table[0].length > j && table.length > i) {
                    //Записать вариант в какую-то структуру данных ArrayList<Integer, Integer>
                }
            }
        }





        return null;
    }


}

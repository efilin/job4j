package ru.headhunter.school.testtask;

import java.util.ArrayList;
import java.util.LinkedList;

public class SplitTheMap {

    char[][] table;
    char[][] tempTable;
    private int vacNumber;
    private int tempVacNumber = 0;
    // Варианты прямоугольников заданной площади
    ArrayList<Pair> rectangleVariants;
    // Начальные точки для прямоугольников
    ArrayList<Pair> startingPoints;

    ArrayList<Pair> removingPoints

    ArrayList<Pair> tempStartingPoints;
    // Последовательность стартовых точек и прямоугольников
    LinkedList<DoublePair> winningCombination;

    //ArrayList<Pair>


    public static void main(String[] args) {
        /**
         *
         *   Получаем таблицу
         * v Вычисляем N
         * v  Пытаемся разбить таблицу на N равных частей с учетом приоритета разбиений
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
     * Убираем варианты которые не помещаются в общую площадь
     * Разработать алгоритм перебора вариантов заполнения общей карты прямоугольниками
     *
     * @param table
     * @return
     */
    private ArrayList<char[][]> parseTable(char[][] table) {
        // Находим количество вакансий
        vacNumber = getN(table);
        // Проверка что общее количество элементов делится нацело на кол-во вакансий
        if (((table[0].length * table.length) % vacNumber) != 0) {
            return null;
        }
        int Square = (table[0].length * table.length) / vacNumber;

        // Перебор вариантов разбиения на прямоугольники при этой площади
        for (int j = 1; j < Square; j++) {
            if (Square % j == 0) {
                int i = Square / j;
                // Проверка на соответсвие размеров частей и целого
                if (table[0].length > j && table.length > i) {
                    // Записать вариант в структуру данных ArrayList<i, j>
                    rectangleVariants.add(new Pair(i, j));
                }
            }
        }

        // Формируем начальный  ArrayList startingPoints = все точки table
        for (int j = 0; j<table[0].length; j++) {
            for (int i = 0; i< table.length; i++) {
                startingPoints.add(new Pair(i,j));
            }
        }

        // Проверяем прямоугольники с одной стартовой точкой
        while (tempVacNumber <= vacNumber) {
            for (Pair recDim : rectangleVariants) {
                if (checkRectangle(startingPoints.get(0), recDim)) {
                    //При успешном заполнении одного из вариантов коррректируем стартовые точки
                    setStartingPointsCorrection(recDim);
                    tempVacNumber++;
                    break;
                }
            }
        }


        // Перебор выриантов заполнения
        // Берем ArrayList[0], вставляем его в table, проверяем на вхождение только одной вакансии
        // Метод checkRectangle()
        // Корректируем ArrayList startingPoints


        return null;
    }

    //Корректировка ArrayList startingPoints
    private void setStartingPointsCorrection(Pair rectDimensions) {
        for (int j = startingPoints.get(0).j; j < startingPoints.get(0).j + rectDimensions.j; j++) {
            for (int i = startingPoints.get(0).i; i < startingPoints.get(0).i + rectDimensions.i; i++) {
                removingPoints.add(new Pair(i, j));
            }
        }
        startingPoints.removeAll(removingPoints);
    }

    //Проверка одного варианта прямоугольника на вхождение
    private boolean checkRectangle(Pair startPoint, Pair rectDimentions) {
        int counter = 0;
        // Проверка влезает ли прямоугольник в table
        if (((startPoint.i + rectDimentions.i) <= table.length)
                && ((startPoint.j + rectDimentions.j) <= table[0].length)) {

            // Проверка на наличие только одной вакансии
            for (int i = startPoint.i; i < startPoint.i + rectDimentions.i; i++) {
                for (int j = startPoint.j; j < startPoint.j + rectDimentions.j; j++) {
                    if (table[i][j] == 'o') {
                        counter++;
                        // Можно добавить условие остановки counter>1
                    }
                }
            }
            return counter == 1;
        }
        return false;
    }

    class Pair {
        private int i;
        private int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (i != pair.i) return false;
            return j == pair.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    class DoublePair{
        Pair startPoint;
        Pair rectDimensions;

        public DoublePair(Pair startPoint, Pair rectDimensions) {
            this.startPoint = startPoint;
            this.rectDimensions = rectDimensions;
        }
    }
}



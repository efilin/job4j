package ru.headhunter.school.testtask;

import ru.job4j.bomberman.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SplitTheMap {

    //
    ArrayList<String> preTable = new ArrayList<>();
    char[][] table;
    //    char[][] tempTable;
    private int vacNumber;
    private int tempVacNumber = 0;
    // Варианты прямоугольников заданной площади
    ArrayList<Pair> rectangleVariants = new ArrayList<>();
    // Начальные точки для прямоугольников
    ArrayList<Pair> startingPoints = new ArrayList<>();

    //ArrayList<Pair> removingPoints;

    //ArrayList<Pair> tempStartingPoints;
    // Последовательность стартовых точек и прямоугольников
    LinkedList<DoublePair> winningCombination = new LinkedList<>();
    //Последовательность уменьшения списка стартовых точек(Чтобы откатывать назад, при необходимости)
    ArrayList<ArrayList<Pair>> startingPointsHistory = new ArrayList<>();

    //ArrayList<Pair>


    public static void main(String[] args) throws FileNotFoundException {
        SplitTheMap splitTheMap = new SplitTheMap();


        // INPUT

        FileReader myFile = new FileReader("input.txt");
        Scanner scanner = new Scanner(myFile);
        ArrayList<String> mapList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            mapList.add(scanner.nextLine());
        }

        splitTheMap.table = splitTheMap.convertListToArray(mapList);



        /*char[][] map = {{'.', 'o', '.', 'o', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'o', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', 'o', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}};*/

        /*char[][] map = {{'o', '.', 'o', '.'},
                {'.', 'o', 'o', '.'}};*/





        /*for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.println(map[i][j]);

            }
        }*/
        long t1 = System.currentTimeMillis();
        if (splitTheMap.parseTable(splitTheMap.table)) {
            System.out.println("Вариант найден");
            // OUTPUT

        } else {
            System.out.println("Вариант не найден");
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

    }

    private char[][] convertListToArray(ArrayList<String> mapList) {
        char[][] map = new char[mapList.get(0).toCharArray().length][mapList.size()];
        for (int i = 0; i < mapList.size(); i++){
            map[i] = mapList.get(i).toCharArray();
        }
        return map;
    }

    private void printResults() {

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
    public boolean parseTable(char[][] table) {
        // Находим количество вакансий
        this.table = table;
        vacNumber = getN(table);
        // Проверка что общее количество элементов делится нацело на кол-во вакансий
        if (((table[0].length * table.length) % vacNumber) != 0) {
            return false;
        }
        // Находим площадь прямоугольников
        int Square = (table[0].length * table.length) / vacNumber;

        // Перебор вариантов разбиения на прямоугольники при этой площади
        for (int j = 1; j <= Square; j++) {
            if (Square % j == 0) {
                int i = Square / j;
                // Проверка на соответсвие размеров частей и целого
                if (table[0].length >= j && table.length >= i) {
                    // Записать вариант в структуру данных ArrayList<i, j>
                    rectangleVariants.add(new Pair(i, j));
                }
            }
        }

        // Формируем начальный  ArrayList startingPoints = все точки table
        for (int j = 0; j < table[0].length; j++) {
            for (int i = 0; i < table.length; i++) {
                startingPoints.add(new Pair(i, j));
            }
        }
        // Добаавляем начальный список в историю
        startingPointsHistory.add(startingPoints);

        // Проверяем прямоугольники с первой стартовой точкой
        int tempRectVariant = 0;
        while (tempVacNumber <= vacNumber) {
            for (int i = tempRectVariant; i < rectangleVariants.size(); i++) {
                if (checkRectangle(startingPointsHistory.get(tempVacNumber).get(0), rectangleVariants.get(i))) {
                    //При успешном заполнении одного из вариантов корректируем стартовые точки
                    startingPointsCorrection(rectangleVariants.get(i));
                    if (startingPoints.size() == 0) return true;
                    //Добавляем измененые стартовые точки в Историю
                    startingPointsHistory.add(startingPoints);
                    //Добавляем стартовую точку и тип фигуры в итоговый массив
                    winningCombination.add(new DoublePair(startingPoints.get(0), i));
                    tempVacNumber++;
                    break;
                }
                // Если ни один вариант не подходит - возвращаемся к предыдущей ступени
                // Реализовать откат до предыдущей ступени
                else if (i == rectangleVariants.size() - 1) {
                    // Условие если нет вариантов в начальной точке
                    if (tempVacNumber == 0) {
                        return false;
                    }
                    //?? winningCombination.removeLast();
                    tempRectVariant = winningCombination.getLast().rectDimensionsVariantNumber + 1;
                    // Если в N-1 не остается вариантов фигур, то возвращием на N-2 и тд;
                    while (tempRectVariant >= rectangleVariants.size()) {
                        //Условие, что нет вариантов заполения не осталось совсем.
                        if (tempVacNumber == 0) {
                            return false;
                        }
                        //Метод возвращающий стартовые точки
                        startingPointsHistory.remove(tempVacNumber);

                        winningCombination.removeLast();

                        // Уменьшаем N
                        tempVacNumber--;
                        // Пересчитываем оставшиеся варианты
                        tempRectVariant = winningCombination.getLast().rectDimensionsVariantNumber + 1;
                    }
                }
            }
        }


        // Перебор выриантов заполнения
        // Берем ArrayList[0], вставляем его в table, проверяем на вхождение только одной вакансии
        // Метод checkRectangle()
        // Корректируем ArrayList startingPoints


        return true;
    }

    //Корректировка ArrayList startingPoints
    private void startingPointsCorrection(Pair rectDimensions) {
        ArrayList<Pair> removingPoints = new ArrayList<>();
        for (int j = startingPoints.get(0).j; j < startingPoints.get(0).j + rectDimensions.j; j++) {
            for (int i = startingPoints.get(0).i; i < startingPoints.get(0).i + rectDimensions.i; i++) {
                removingPoints.add(new Pair(i, j));
            }
        }
        startingPoints.removeAll(removingPoints);
    }

    //Проверка одного варианта прямоугольника на вхождение
    //TODO Проверить метод дополнительно для tempVacNumber>1, на вхождение в starting points
    private boolean checkRectangle(Pair startPoint, Pair rectDimensions) {
        int counter = 0;
        // Проверка влезает ли прямоугольник в table
        if (((startPoint.i + rectDimensions.i) <= table.length)
                && ((startPoint.j + rectDimensions.j) <= table[0].length)) {

            // Проверка на наличие только одной вакансии
            for (int i = startPoint.i; i < startPoint.i + rectDimensions.i; i++) {
                for (int j = startPoint.j; j < startPoint.j + rectDimensions.j; j++) {
                    if (table[i][j] == 'o') {
                        counter++;
                        // Можно добавить условие остановки if (counter>1)
                    }
                }
            }
            //TODO
            return counter == 1 && checkIncludingRectIntoStartingList(startingPoints, rectDimensions);
        }
        return false;
    }

    // Проверка на вхождение в стартовый лист
    // TODO Проверить метод contains
    private boolean checkIncludingRectIntoStartingList(ArrayList<Pair> startingPoints, Pair rectDimensions) {
        ArrayList<Pair> removingPoints = new ArrayList<>();
        for (int j = startingPoints.get(0).j; j < startingPoints.get(0).j + rectDimensions.j; j++) {
            for (int i = startingPoints.get(0).i; i < startingPoints.get(0).i + rectDimensions.i; i++) {
                removingPoints.add(new Pair(i, j));
            }
        }
        Set<Pair> startingPointsSet = new HashSet<>(startingPoints);
        Set<Pair> removingPointsSet = new HashSet<>(removingPoints);
        return startingPointsSet.containsAll(removingPointsSet);
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

    class DoublePair {
        Pair startPoint;
        int rectDimensionsVariantNumber;

        public DoublePair(Pair startPoint, int rectDimensionsVariantNumber) {
            this.startPoint = startPoint;
            this.rectDimensionsVariantNumber = rectDimensionsVariantNumber;
        }
    }
}



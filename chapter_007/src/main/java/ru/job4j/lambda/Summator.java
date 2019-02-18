package ru.job4j.lambda;

        import java.util.Arrays;

public class Summator {


   /* Задан массив чисел.
      1. Нужно него отфильтровать, оставить только четные числа.
      2. Каждое число возвести в квадрат.
      3. И все элементы просуммировать
    */

    public int filterCalcAndSum(int[] array) {
        return Arrays.stream(array)
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .reduce(0, (x, y) -> x + y);
    }
}

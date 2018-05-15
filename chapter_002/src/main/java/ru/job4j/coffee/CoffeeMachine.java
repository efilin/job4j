package ru.job4j.coffee;

import java.util.Arrays;

public class CoffeeMachine {

    int[] changes(int value, int price) {
        int[] coins = {10, 5, 2, 1};
        int[] change = new int[100];
        int i = 0;
        int j = 0;
        int changeTotal = value - price;
        while (changeTotal != 0 && j != 4) {
            if (changeTotal - coins[j] >= 0) {
                change[i]  =  coins[j];
                changeTotal -= coins[j];
                i++;
            } else {
                j++;
            }
        }return Arrays.copyOf(change,i);
    }
}
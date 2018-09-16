package ru.headhunter.school.testtask;

import java.util.HashSet;
import java.util.Scanner;

public class MinMissedNumber {

    private HashSet<Integer> data = new HashSet<>();



    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        MinMissedNumber minMissedNumber = new MinMissedNumber();
        minMissedNumber.getMinMissNumber(input);
    }

    private void transferStringToSet(String input) {
        String[] strings = input.split(" ");
        for (int index = 0; index < strings.length; index++) {
            data.add(Integer.valueOf(strings[index]));
        }
    }

    public int getMinMissNumber(String input) {
        transferStringToSet(input);
        for (int i = 1; i < 1000000000; i++) {
            if (!data.contains(i)) {
                System.out.println(i);
                return i;
            }
        }
        return 0;
    }
}

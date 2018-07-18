package ru.job4j.sort;

import java.util.*;

public class DepartmentsSort {


    public String[] ascendingSort(String[] departments) {
        ArrayList<String> result = getMissedDep(departments);
        TreeSet<String> treeSet = new TreeSet<>(result);
        return treeSet.toArray(new String[0]);
    }

    public String[] descendingSort(String[] departments) {
        ArrayList<String> result = getMissedDep(departments);
        result.sort(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            int n1 = o1.length();
                            int n2 = o2.length();
                            int min = Math.min(n1, n2);
                            for (int i = 0; i < min; i++) {
                                char c1 = o1.charAt(i);
                                char c2 = o2.charAt(i);
                                if (c1 != c2) {
                                    return c2 - c1;
                                }
                            }
                            return n1 - n2;
                        }
                    }
        );
        return result.toArray(new String[0]);
    }


    public ArrayList<String> getMissedDep(String[] departments) {
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(departments));
        ArrayList<String> missedElements = new ArrayList<>();
        for (String el : result) {
            for (int index = 0; index < el.length() - 1; index++) {
                if (el.substring(index, index + 1).equals("\\")) {
                    if (!result.contains(el.substring(0, index)) && !missedElements.contains(el.substring(0, index))) {
                        missedElements.add(el.substring(0, index));
                    }
                }
            }
        }
        result.addAll(missedElements);
        return result;
    }
}

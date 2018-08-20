package ru.job4j.sort;


import java.util.*;

public class DepartmentsSort {


    public String[] ascendingSort(String[] departments) {
        Set<String> treeSet = getMissedDep(departments);
        return treeSet.toArray(new String[0]);
    }

    public String[] descendingSort(String[] departments) {
        ArrayList<String> result = new ArrayList<>(getMissedDep(departments));
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

    public Set<String> getMissedDep(String[] departments) {
        Set<String> result = new TreeSet<>(Arrays.asList(departments));
        for (String el : departments) {
            for (int index = 0; index < el.length() - 1; index++) {
                if (el.substring(index, index + 1).equals("\\")) {
                    result.add(el.substring(0, index));
                }
            }
        }
        return result;
    }
}

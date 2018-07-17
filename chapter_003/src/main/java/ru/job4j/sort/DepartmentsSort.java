package ru.job4j.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DepartmentsSort {

    private String[] departments;


    public String[] ascendingSort(String[] departments) {
        ArrayList<String> result  = getMissedDep(departments);
        TreeSet<String> treeSet = new TreeSet<>(result);

        //to do

        return treeSet.toArray(new String[0]);
    }

    public String[] descendingSort(String[] departments) {
        ArrayList<String> result  = getMissedDep(departments);
        TreeSet<String> treeSet = new TreeSet<>(result);
        NavigableSet<String> sortedSet = treeSet.descendingSet();
        return sortedSet.toArray(new String[0]);
    }


    public ArrayList<String> getMissedDep(String[] departments) {
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(departments));
        ArrayList<String> missedElements = new ArrayList<>();
        for (String el: result) {
            for (int index = 0; index < el.length() - 1; index++) {
                if (el.substring(index, index + 1).equals("\\")) {
                    if (!result.contains(el.substring(0,index))) {
                        missedElements.add(el.substring(0, index));
                    }
                }
            }
        }
        result.addAll(missedElements);
        return result;
    }

    public String[] getDepartments() {
        return departments;
    }

    public void setDepartments(String[] departments) {
        this.departments = departments;
    }
}

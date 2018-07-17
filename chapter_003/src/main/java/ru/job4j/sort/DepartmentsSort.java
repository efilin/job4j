package ru.job4j.sort;

public class DepartmentsSort {

    private String[] departments;


    public String[] ascendingSort(String[] departments) {

        String[] result = new String[departments.length + getMissedDep(departments).length];


        return result;
    }

    public String[] descendingSort(String[] departments) {
        String[] result = new String[departments.length + getMissedDep(departments).length];
        return result;
    }


    public String[] getMissedDep(String[] departments) {

        String[] result = new String[departments.length];
        return result;
    }

    public String[] getDepartments() {
        return departments;
    }

    public void setDepartments(String[] departments) {
        this.departments = departments;
    }
}

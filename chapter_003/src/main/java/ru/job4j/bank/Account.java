package ru.job4j.bank;


public class Account {
    private int value;
    private String requisites;

    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }



    public int getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(int value) {
        this.value = value;
    }

    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount < this.value && destination != null) {
            success = true;
            this.value -= amount;
            destination.value += amount;
        }
        return success;
    }
}

package ru.job4j.cinema;

public class Account {

    private String name;
    private long phone;

    public Account(String name, long phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;

        if (phone != account.phone) {
            return false;
        }
        return name != null ? name.equals(account.name) : account.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (phone ^ (phone >>> 32));
        return result;
    }
}

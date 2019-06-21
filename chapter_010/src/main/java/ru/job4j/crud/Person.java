package ru.job4j.crud;

public class Person {
    private String name;
    private String lastName;
    private String gender;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format(
                " name: %s, lastname: %s, gender: %s, desc: %s ",
                name, lastName, gender, desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        if (!name.equals(person.name)) {
            return false;
        }
        if (!lastName.equals(person.lastName)) {
            return false;
        }
        if (!gender.equals(person.gender)) {
            return false;
        }
        return desc != null ? desc.equals(person.desc) : person.desc == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }
}

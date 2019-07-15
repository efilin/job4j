package ru.job4j.crud;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private String createDate;
    private String password;
    private String role;
    private String country;
    private String city;

    public User(String name, String password, String role, String country, String city) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.country = country;
        this.city = city;
    }

    public User(int id, String name, String password, String role, String country, String city) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate='" + createDate + '\''
                + ", password='" + password + '\''
                + ", role='" + role + '\''
                + ", country='" + country + '\''
                + ", city='" + city + '\''
                + '}';
    }
}

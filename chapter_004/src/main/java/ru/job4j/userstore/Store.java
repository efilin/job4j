package ru.job4j.userstore;

import java.util.List;

class Store {

    public Info diff(List<User> previous, List<User> current) {
        int sameIdUsers = 0;
        int changedUsers = 0;
        for (User userPr : previous) {
            for (User userCu : current) {
                if (userPr.id == userCu.id) {
                    sameIdUsers++;
                    if ((!userPr.name.equals(userCu.name))) {
                        changedUsers++;
                    }
                }
            }
        }
        int addedUsers = current.size() - sameIdUsers;
        int deletedUsers = previous.size() - sameIdUsers;
        return new Info(addedUsers, changedUsers, deletedUsers);
    }

    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
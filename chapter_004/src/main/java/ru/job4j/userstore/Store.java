package ru.job4j.userstore;

import java.util.HashMap;
import java.util.List;

class Store {

    public Info diff(List<User> previous, List<User> current) {

        HashMap<Integer, String> prevHashMap = new HashMap<>();
        HashMap<Integer, String> currHashMap = new HashMap<>();

        for (User userPr : previous) {
            prevHashMap.put(userPr.id, userPr.name);
        }

        for (User userCu : current) {
            currHashMap.put(userCu.id, userCu.name);
        }
        int sameIdUsers = 0;
        int changedUsers = 0;
        for (Integer keyId : prevHashMap.keySet()) {
            if (currHashMap.keySet().contains(keyId)) {
                sameIdUsers++;
                if (!currHashMap.get(keyId).equals(prevHashMap.get(keyId))) {
                    changedUsers++;
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
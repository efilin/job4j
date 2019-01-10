package ru.job4j.bank;

import java.util.*;

/**
 * Изменено на Stream API
 */

public class Bank {
    Map<User, List<Account>> users;

    public Bank() {
        this.users = new TreeMap<>();
    }

    public void addUser(User user) {
        this.users.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.users.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        getUserAccounts(passport).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        getUserAccounts(passport).remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return this.users.get(getUserByPassport(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        return this.users.get(getUserByPassport(srcPassport)).contains(getAccountByRequisites(getUserByPassport(srcPassport), srcRequisite))
                && this.users.get(getUserByPassport(destPassport)).contains(getAccountByRequisites(getUserByPassport(destPassport), destRequisite))
                && getAccountByRequisites(getUserByPassport(srcPassport), srcRequisite).transfer(getAccountByRequisites(getUserByPassport(destPassport), destRequisite), amount);
    }


    public User getUserByPassport(String passport) {
        /*User result = null;
        for (User user : this.users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = new User(user.getName(), user.getPassport());
                break;
            }
        }*/
        return users.keySet().stream()
                .filter(u -> u.getPassport().equals(passport)).findFirst().get();
    }

    public Account getAccountByRequisites(User user, String requisites) {
        /*Account result = null;
        for (Account account : this.users.get(user)) {
            if (requisites.equals(account.getRequisites())) {
                result = account;
                break;
            }
        }*/
        return users.entrySet().stream()
                .filter(u ->  u.getKey().equals(user))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .filter(a -> a.getRequisites().equals(requisites)).findFirst().get();
    }
}

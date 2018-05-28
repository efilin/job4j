package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
    Map<User, List<Account>> users;

    public Bank() {
        this.users = new TreeMap<User, List<Account>>();
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

    public List<Account> getUserAccounts (String passport) {
        return this.users.get(getUserByPassport(passport));
    }

    public boolean transferMoney (String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        return this.users.get(getUserByPassport(srcPassport)).contains(getAccountByRequisites(getUserByPassport(srcPassport), srcRequisite))
                && this.users.get(getUserByPassport(destPassport)).contains(getAccountByRequisites(getUserByPassport(destPassport), destPassport))
                && getAccountByRequisites(getUserByPassport(srcPassport), srcRequisite).transfer(getAccountByRequisites(getUserByPassport(destPassport), dstRequisite),amount);
    }

    public User getUserByPassport (String passport) {
        User result = null;
        for (User user : this.users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = new User(user.getName(), user.getPassport());
                break;
            }
        }
        return result;
    }

    public Account getAccountByRequisites (User user, String requisites) {
        Account result = null;
        for (Account account : this.users.get(user)) {
            if (requisites.equals(account.getRequisites())) {
                result = account;
                break;
            }
        }
        return result;
    }
}

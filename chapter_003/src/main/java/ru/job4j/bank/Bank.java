package ru.job4j.bank;

import java.util.List;
import java.util.Map;

public class Bank {
    Map<User, List<Account>> users;

    public Bank(Map<User, List<Account>> users) {
        this.users = users;
    }

    public void addUser(User user) { }

    public void deleteUser(User user) { }

    public void addAccountToUser(String passport, Account account) { }

    public void deleteAccountFromUser(String passport, Account account) { }

    //public List<Accounts> getUserAccounts (String passport) {}

    //public boolean transferMoney (String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount)






}

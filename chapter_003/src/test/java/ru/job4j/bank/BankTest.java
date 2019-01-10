package ru.job4j.bank;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void addUser() {
        Bank bank = new Bank();
        User userOne = new User("Alex", "12345");
        bank.addUser(userOne);
        User result = bank.getUserByPassport("12345");
        assertThat(userOne, is(result));
    }

    @Test
    public void deleteUser() {
        Bank result = new Bank();
        Bank expect =  new Bank();
        User userOne = new User("Alex", "12345");
        User userTwo = new User("Tom", "54321");
        result.addUser(userOne);
        result.addUser(userTwo);
        result.deleteUser(userTwo);
        expect.addUser(userOne);
        assertThat(expect.getUserByPassport("12345"), is(result.getUserByPassport("12345")));
    }

    @Test
    public void addAccountToUser() {
        Bank bank = new Bank();
        User userOne = new User("Alex", "12345");
        Account account = new Account(15000, "97979797");
        bank.addUser(userOne);
        bank.addAccountToUser("12345", account);
        Account expect = bank.getAccountByRequisites(userOne, "97979797");
        assertThat(expect, is(account));
    }

    @Test
    public void deleteAccountFromUser() {
        Bank bank = new Bank();
        User userOne = new User("Alex", "12345");
        Account accountOne = new Account(5000, "111111");
        Account accountTwo = new Account(10000, "222222");
        bank.addUser(userOne);
        bank.addAccountToUser("12345", accountOne);
        List<Account> expect = bank.getUserAccounts("12345");
        bank.addAccountToUser("12345", accountTwo);
        bank.deleteAccountFromUser("12345", accountTwo);
        List<Account> result = bank.getUserAccounts("12345");
        assertThat(expect, is(result));
    }

    @Test
    public void transferMoney() {
        Bank bank = new Bank();
        User userOne = new User("Alex", "12345");
        User userTwo = new User("Tom", "54321");
        bank.addUser(userOne);
        bank.addUser(userTwo);
        Account accountOne = new Account(5000, "111111");
        Account accountTwo = new Account(10000, "222222");
        int expect = accountOne.getValue() - 1000;
        bank.addAccountToUser("12345", accountOne);
        bank.addAccountToUser("54321", accountTwo);
        bank.transferMoney("12345", "111111", "54321", "222222", 1000);
        int result = bank.getAccountByRequisites(userOne, "111111").getValue();
        assertThat(result, is(expect));
    }
}
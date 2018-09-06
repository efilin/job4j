package ru.job4j.pool;

import org.junit.Test;


public class EmailNotificationTest {

    EmailNotification emailNotification;

    @Test
    public void emailTo() {
        emailNotification = new EmailNotification();
        User userOne = new User("Alex", "alex@ya.ru");
        User userTwo = new User("Bob", "bob@ya.ru");
        User userThree = new User("Chris", "chris@ya.ru");
        User userFour = new User("Dirk", "dirk@ya.ru");
        User userFive = new User("Eugene", "eugene@ya.ru");
        emailNotification.emailTo(userOne);
        emailNotification.emailTo(userTwo);
        emailNotification.emailTo(userThree);
        emailNotification.emailTo(userFour);
        emailNotification.emailTo(userFive);
        emailNotification.close();
    }
}

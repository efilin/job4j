package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    //private User user;
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * Метод берет данные пользователя
     * и подставляет в шаблон.
     *
     * @param user user
     */
    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
                String subject = "Notification " + user.getUsername() + " to email " + user.getEmail();
                String body = "Add a new event to " + user.getUsername();
                EmailNotification.this.send(subject, body, user.getEmail());
            }
        });
    }

    /**
     * Метод, закрывающий pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void send(String subject, String body, String email) {
        System.out.println(subject);
        System.out.println(body);
        System.out.println(email);
    }
}

package ru.job4j.storage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.job4j.beanexamples.User;

@Component
public class ImportUser {

    private Storage storage;

    @Autowired
    public ImportUser(@Qualifier("jdbcStorage") Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ImportUser importUser = context.getBean(ImportUser.class);
        importUser.add(new User("Mike"));
    }
}

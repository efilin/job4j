package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class StartUITest {

    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 2));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"1", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenEdit() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 3));
        Input input = new StubInput(new String[]{"2", item.getId(), "test name2", "desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name2"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"3", itemOne.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name2"));


    }
    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\\r\\n" +
                                        "0. Добавить заявку\\r\\n" +
                                        "1. Показать все записи\\r\\n" +
                                        "2. Изменить заявку по id\\r\\n" +
                                        "3. Удалить заявку по id\\r\\n" +
                                        "4. Найти заявку по id\\r\\n" +
                                        "5. Найти все заявки по имени\\r\\n" +
                                        "6. Выход\\r\\n" +
                                        "------------ Отображение всех записей --------------\\r\\n" +
                                        "----------- Заявка id : "+ itemOne.getId() +"---------------\\r\\n" +
                                        "----------- Имя : test name----------------\\r\\n" +
                                        "----------- Описание : desc---------------\\r\\n" +
                                        "----------- Заявка id : " + itemTwo.getId() + "---------------\\r\\n" +
                                        "----------- Имя : test name2----------------\\r\\n" +
                                        "----------- Описание : desc2---------------\\r\\nМеню.\\r\\n" +
                                        "0. Добавить заявку\\r\\n" +
                                        "1. Показать все записи\\r\\n" +
                                        "2. Изменить заявку по id\\r\\n" +
                                        "3. Удалить заявку по id\\r\\n" +
                                        "4. Найти заявку по id\\r\\n" +
                                        "5. Найти все заявки по имени\\r\\n" +
                                        "6. Выход\\r\\n")
                                .toString()
                )
        );

    }

    @Test
    public void whenFindId() {


    }

    @Test
    public  void whenFindName() {


    }

}

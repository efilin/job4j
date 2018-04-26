package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class StartUITest {

    private final PrintStream stdout = System.out;
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
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }



    @Test
    public void whenEdit() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 3));
        Input input = new StubInput(new String[]{"2", item.getId(), "test name2", "desc2", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name2"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"3", itemOne.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name2"));


    }
    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add the new item. \r\n" +
                                        "1. show all item.\r\n" +
                                        "2. Edit the new item. \r\n" +
                                        "3. Delete item by id: \r\n" +
                                        "4. Find item by id: \r\n" +
                                        "5. Find items by name: \r\n" +
                                        "Name: test name| Desc: desc| Id: " + itemOne.getId() + "\r\n" +
                                        "Name: test name2| Desc: desc2| Id: " + itemTwo.getId() + "\r\n")
                                .toString()
                )
        );

    }

    @Test
    public void whenFindId() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"4",itemTwo.getId(),"y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add the new item. \r\n" +
                                        "1. show all item.\r\n" +
                                        "2. Edit the new item. \r\n" +
                                        "3. Delete item by id: \r\n" +
                                        "4. Find item by id: \r\n" +
                                        "5. Find items by name: \r\n" +
                                        "Name: test name2| Desc: desc2| Id: " + itemTwo.getId() + "\r\n")
                                .toString()
                )

        );
    }

    @Test
    public  void whenFindName() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Item itemThree = tracker.add(new Item("test name", "desc3", 5));
        Input input = new StubInput(new String[]{"5","test name","y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add the new item. \r\n" +
                                        "1. show all item.\r\n" +
                                        "2. Edit the new item. \r\n" +
                                        "3. Delete item by id: \r\n" +
                                        "4. Find item by id: \r\n" +
                                        "5. Find items by name: \r\n" +
                                        "Name: test name| Desc: desc| Id: " + itemOne.getId() + "\r\n" +
                                        "Name: test name| Desc: desc3| Id: " + itemThree.getId() + "\r\n")
                                .toString()
                )
        );


    }

}

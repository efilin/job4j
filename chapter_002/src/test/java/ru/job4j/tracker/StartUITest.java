package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class StartUITest {



    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = System.out;
        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return new String(out.toByteArray());
        }
    };

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /*@After
    public void backOutput() {
        //System.setOut(this.stdout);
        System.out.println("execute after method");
    }*/

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }



    @Test
    public void whenEdit() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 3));
        Input input = new StubInput(new String[]{"2", String.format("%s", item.getId()), "test name2", "desc2", "y"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name2"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"3", String.format("%s", itemOne.getId()), "y"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name2"));
    }

    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append("0 : Add the new item")
                                .append(System.lineSeparator())
                                .append("1 : Show all items")
                                .append(System.lineSeparator())
                                .append("2 : Edit item")
                                .append(System.lineSeparator())
                                .append("3 : Delete item by id")
                                .append(System.lineSeparator())
                                .append("4 : Find item by id")
                                .append(System.lineSeparator())
                                .append("5 : Find items by name")
                                .append(System.lineSeparator())
                                .append("Name: test name| Desc: desc| Id: " + itemOne.getId())
                                .append(System.lineSeparator())
                                .append("Name: test name2| Desc: desc2| Id: " + itemTwo.getId())
                                .append(System.lineSeparator())
                                .toString()
                )
        );

    }

    @Test
    public void whenFindId() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("test name", "desc", 3));
        Item itemTwo = tracker.add(new Item("test name2", "desc2", 4));
        Input input = new StubInput(new String[]{"4", String.format("%s", itemTwo.getId()), "y"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append("0 : Add the new item")
                                .append(System.lineSeparator())
                                .append("1 : Show all items")
                                .append(System.lineSeparator())
                                .append("2 : Edit item")
                                .append(System.lineSeparator())
                                .append("3 : Delete item by id")
                                .append(System.lineSeparator())
                                .append("4 : Find item by id")
                                .append(System.lineSeparator())
                                .append("5 : Find items by name")
                                .append(System.lineSeparator())
                                .append("Name: test name2| Desc: desc2| Id: " + itemTwo.getId())
                                .append(System.lineSeparator())
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
        Input input = new StubInput(new String[]{"5", "test name", "y"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuilder()
                                .append("0 : Add the new item")
                                .append(System.lineSeparator())
                                .append("1 : Show all items")
                                .append(System.lineSeparator())
                                .append("2 : Edit item")
                                .append(System.lineSeparator())
                                .append("3 : Delete item by id")
                                .append(System.lineSeparator())
                                .append("4 : Find item by id")
                                .append(System.lineSeparator())
                                .append("5 : Find items by name")
                                .append(System.lineSeparator())
                                .append("Name: test name| Desc: desc| Id: " + itemOne.getId())
                                .append(System.lineSeparator())
                                .append("Name: test name| Desc: desc3| Id: " + itemThree.getId())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}

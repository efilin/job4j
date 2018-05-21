package ru.job4j.tracker;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));

    }
    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("test1", "testDescription", 123L);
        tracker.add(itemOne);
        Item itemTwo = new Item("test2", "testDescription2", 124L);
        tracker.add(itemTwo);
        Item itemThree = new Item("test3", "testDescription3", 125L);
        tracker.add(itemThree);
        assertThat(tracker.findById(itemOne.getId()).getName(), is("test1"));

    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        List<Item> items = new ArrayList<>();
        Item itemOne = new Item("test1", "testDescription", 123L);
        tracker.add(itemOne);
        Item itemTwo = new Item("test2", "testDescription2", 124L);
        tracker.add(itemTwo);
        Item itemThree = new Item("test3", "testDescription3", 125L);
        tracker.add(itemThree);
        items.add(itemOne);
        items.add(itemTwo);
        items.add(itemThree);
        assertThat(tracker.findAll(), is(items));

    }

    @Test
    public  void whenDelete() {
        Tracker tracker = new Tracker();
        List<Item> items = new ArrayList<>();
        Item itemOne = new Item("test1", "testDescription", 123L);
        tracker.add(itemOne);
        Item itemTwo = new Item("test2", "testDescription2", 124L);
        tracker.add(itemTwo);
        Item itemThree = new Item("test3", "testDescription3", 125L);
        tracker.add(itemThree);
        Item itemFour = new Item("test4", "testDescription4", 126L);
        tracker.add(itemFour);
        tracker.delete(itemThree.getId());
        items.add(itemOne);
        items.add(itemTwo);
        items.add(itemFour);
        assertThat(tracker.findAll(), is(items));

    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("test1", "testDescription", 123L);
        tracker.add(itemOne);
        Item itemTwo = new Item("test2", "testDescription2", 124L);
        tracker.add(itemTwo);
        Item itemThree = new Item("test3", "testDescription3", 125L);
        tracker.add(itemThree);
        assertThat(tracker.findByName("test2").get(0), is(tracker.findById(itemTwo.getId())));
    }
}

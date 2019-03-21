package ru.job4j.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenAddMilkToStorage() {
        ControlQuality cq = new ControlQuality();
        Food milk = new Food("milk", LocalDate.now(),
                LocalDate.now().plusDays(8), 60);
        cq.add(milk);
        assertThat(true, is(cq.load().get(0).getStorage().contains(milk)));
    }

    @Test
    public void whenAddCheeseToStorage() {
        ControlQuality cq = new ControlQuality();
        Food cheese = new Food("milk", LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10), 600);
        cq.add(cheese);
        assertThat(true, is(cq.load().get(1).getStorage().contains(cheese)));
    }

    @Test
    public void whenAddBreadToStorage() {
        ControlQuality cq = new ControlQuality();
        Food bread = new Food("milk", LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(2), 30);
        cq.add(bread);
        assertThat(true, is(cq.load().get(2).getStorage().contains(bread)));
    }

}
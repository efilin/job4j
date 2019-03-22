package ru.job4j.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenAddMilkToStorage() {
        ControlQuality cq = new ControlQuality();
        Food milk = new Food("milk", LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(8), 60, false, false);
        cq.add(milk);
        assertThat(cq.load().get(1).getStorage().contains(milk), is(true));
    }

    @Test
    public void whenAddCheeseToStorage() {
        ControlQuality cq = new ControlQuality();
        Food cheese = new Food("cheese", LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10), 600, false, false);
        cq.add(cheese);
        assertThat(cq.load().get(3).getStorage().contains(cheese), is(true));
    }

    @Test
    public void whenAddBreadToStorage() {
        ControlQuality cq = new ControlQuality();
        Food bread = new Food("bread", LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(2), 30, false, false);
        cq.add(bread);
        assertThat(cq.load().get(5).getStorage().contains(bread), is(true));
    }

    @Test
    public void whenAddReproductableBreadToStorage() {
        ControlQuality cq = new ControlQuality();
        Food bread = new Food("reproductableBread", LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(2), 30, true, false);
        cq.add(bread);
        assertThat(cq.load().get(4).getStorage().contains(bread), is(true));
    }

    @Test
    public void whenAddPepperToStorage() {
        ControlQuality cq = new ControlQuality();
        Food pepper = new Food("pepper", LocalDate.now(),
                LocalDate.now().plusDays(8), 180, false, true);
        cq.add(pepper);
        assertThat(cq.load().get(0).getStorage().contains(pepper), is(true));
    }

}
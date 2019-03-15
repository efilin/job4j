package ru.job4j.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void whenAddMilkToStorage() {
        Client client = new Client();
        Food milk = new Food("milk", LocalDate.now(),
                LocalDate.now().plusDays(8), 60);
        client.addFoodToStorages(milk);
        assertThat(true, is(client.getWarehouse().getStorage().contains(milk)));
    }

    @Test
    public void whenAddCheeseToStorage() {
        Client client = new Client();
        Food cheese = new Food("milk", LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10), 600);
        client.addFoodToStorages(cheese);
        assertThat(true, is(client.getShop().getStorage().contains(cheese)));
    }

    @Test
    public void whenAddBreadToStorage() {
        Client client = new Client();
        Food bread = new Food("milk", LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(2), 30);
        client.addFoodToStorages(bread);
        assertThat(true, is(client.getTrash().getStorage().contains(bread)));
    }
}
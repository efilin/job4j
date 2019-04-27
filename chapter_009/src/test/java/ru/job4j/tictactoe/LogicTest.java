package ru.job4j.tictactoe;

import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LogicTest {

    @Test
    public void whenCrossesWin() {
        Logic logic = new Logic(new Scanner(System.in), new Field(2), 2);
        logic.setX(0, 0);
        logic.setX(1, 0);
        assertThat(logic.hasWinner(2), is(true));
    }

    @Test
    public void whenZeroesWin() {
        Logic logic = new Logic(new Scanner(System.in), new Field(2), 2);
        logic.setO(0, 0);
        logic.setO(1, 0);
        assertThat(logic.isWin('O'), is(true));
    }

    @Test
    public void whenComputerMoveTillNoFreeSpaces() {
        Logic logic = new Logic(new Scanner(System.in), new Field(2), 2);
        logic.move(2);
        logic.move(2);
        logic.move(2);
        assertThat(logic.hasGap(), is(true));
        logic.computerMove();
        assertThat(logic.hasGap(), is(false));
    }

    @Test
    public void whenInvalidInput() {
        Logic logic = new Logic(new Scanner(System.in), new Field(2), 2);
        assertThat(logic.inputValidate("2", "2"), is(false));
    }
}
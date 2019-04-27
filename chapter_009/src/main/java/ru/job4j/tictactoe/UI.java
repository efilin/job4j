package ru.job4j.tictactoe;

import java.util.Scanner;

public class UI {

    private static final String START_MESSAGE = "Программа крестики-нолики";

    private Logic logic;
    private boolean isHumanFirst;


    public UI(boolean isHumanFirst, Logic logic) {
        this.isHumanFirst = isHumanFirst;
        this.logic = logic;
    }


    public void show() {
        int moveCounter = 0;
        if (isHumanFirst) {
            moveCounter++;
        }
        System.out.println(START_MESSAGE);
        this.logic.printField();
        do {
            this.logic.move(moveCounter++);
        } while (!this.logic.hasWinner(moveCounter) && this.logic.hasGap());
    }
}

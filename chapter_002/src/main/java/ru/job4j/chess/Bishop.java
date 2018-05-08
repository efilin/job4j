package ru.job4j.chess;

public class Bishop implements Figure {



    Figure copy(Cell dest) {
        return new Bishop(dest);
    }



}

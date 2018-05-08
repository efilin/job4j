package ru.job4j.chess;

public  abstract class Figure {

    final Cell position;

    public Figure() {

    }

    abstract Cell[] way(Cell source, Cell dest) throw ImposibleMoveException

}


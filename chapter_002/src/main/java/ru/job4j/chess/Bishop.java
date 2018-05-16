package ru.job4j.chess;

public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }


    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.x - source.x) != Math.abs(dest.y - source.y) || (dest.x - source.x) == 0){
            throw  new ImpossibleMoveException();
        }
        int steps = Math.abs(dest.x - source.x);
        Cell[] wayPoints = new Cell[steps];
        int index = 0;
        while ((dest.x - source.x) != 0) {
            int stepX = (dest.x - source.x) > 0 ? source.x++ : source.x--;
            int stepY = (dest.y - source.y) > 0 ? source.y++ : source.y--;
            wayPoints[index] = new Cell (stepX, stepY);
            index++;
        }
        return wayPoints;
    }


    Figure copy(Cell dest) {
        return new Bishop(dest);
    }



}

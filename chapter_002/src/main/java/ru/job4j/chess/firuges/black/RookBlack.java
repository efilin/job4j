package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack implements Figure {
    private final Cell position;

    public RookBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    public boolean isCorrectMove(Cell source, Cell dest) {
        return ((source.x - dest.x) == 0 || (source.y - dest.y) == 0);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isCorrectMove(source, dest)) {
            throw new ImpossibleMoveException();
        }
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        int size = Math.abs(source.x - dest.x) + Math.abs(source.y - dest.y);
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            int x = source.x + (i + 1) * deltaX;
            int y = source.y + (i + 1) * deltaY;
            steps[i] = Cell.values()[8 * x + y];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}

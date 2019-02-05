package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    public boolean isCorrectMove(Cell source, Cell dest) {
        return (source.y == 6 && (source.y == dest.y + 1 || source.y == dest.y + 2) && source.x == dest.x)
                || (source.y == dest.y + 1 && source.x == dest.x);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isCorrectMove(source, dest)) {
            throw new ImpossibleMoveException();
        }
        int deltaY = Integer.compare(dest.y, source.y);
        int size = Math.abs(source.y - dest.y);
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            int y = source.y + (i + 1) * deltaY;
            steps[i] = Cell.values()[8 * source.x + y];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}

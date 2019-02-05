package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    public boolean isCorrectMove(Cell source, Cell dest) {
        return (Math.abs(source.x - dest.x) == 1 && Math.abs(source.y - dest.y) == 2) ||
                (Math.abs(source.x - dest.x) == 2 && Math.abs(source.y - dest.y) == 1);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isCorrectMove(source, dest)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}

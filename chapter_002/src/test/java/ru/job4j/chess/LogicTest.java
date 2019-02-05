package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.BishopWhite;
import ru.job4j.chess.firuges.white.KnightWhite;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LogicTest {

    @Test
    public void whenBishopBlackRightDiagonalThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.B1));
        boolean result = logic.move(Cell.B1, Cell.H7);
        assertThat(result, is(true));
    }

    @Test
    public void whenKnightBlackMoveThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new KnightWhite(Cell.B1));
        boolean result = logic.move(Cell.B1, Cell.C3);
        assertThat(result, is(true));
    }

    @Test
    public void whenKingBlackMoveForwardThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new KingBlack(Cell.D1));
        boolean result = logic.move(Cell.D1, Cell.D2);
        assertThat(result, is(true));
    }

    @Test
    public void whenPawnWhiteMoveThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new PawnWhite(Cell.B6));
        boolean result = logic.move(Cell.B6, Cell.B7);
        assertThat(result, is(true));
    }

    @Test
    public void whenPawnBlackMoveThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.A7));
        boolean result = logic.move(Cell.A7, Cell.A6);
        assertThat(result, is(true));
    }

    @Test
    public void whenQeenBlackMoveDiagonalThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new QueenBlack(Cell.D1));
        boolean result = logic.move(Cell.D1, Cell.H5);
        assertThat(result, is(true));
    }

    @Test
    public void whenRookBlackMoveForwardThenTrue() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new RookBlack(Cell.A3));
        boolean result = logic.move(Cell.A3, Cell.A8);
        assertThat(result, is(true));
    }

    @Test
    public void whenBishopBlackWayThenCellSteps() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.B1);
        Cell[] result = bishopBlack.way(Cell.B1, Cell.D3);
        Cell[] expect = {Cell.C2, Cell.D3};
        assertThat(result, is(expect));
    }

    @Test
    public void whenRookBlackWayThenCellSteps() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.H1);
        Cell[] result = rookBlack.way(Cell.H1, Cell.H4);
        Cell[] expect = {Cell.H2, Cell.H3, Cell.H4};
        assertThat(result, is(expect));
    }

    @Test(expected = OccupiedWayException.class)
    public void whenOccupiedWayException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.B1));
        logic.add(new BishopWhite(Cell.C2));
        logic.move(Cell.B1, Cell.D3);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenImpossibleMoveException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
            Logic logic = new Logic();
            logic.add(new BishopBlack(Cell.E2));
            logic.move(Cell.E2, Cell.E4);
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenFigureNotFoundException() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
            Logic logic = new Logic();
            logic.move(Cell.E2, Cell.E4);
    }
}
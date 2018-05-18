package ru.job4j.chess;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class BoardTest {

    @Test
    public void whenCorrectMove() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell cellSource = new Cell(2, 2);
        Cell cellDest = new Cell(5, 5);
        Bishop bishop = new Bishop(cellSource);
        board.add(bishop);
        board.move((cellSource), cellDest);
        assertThat(bishop.position.x, is(cellDest.x));
        assertThat(bishop.position.y, is(cellDest.y));
    }

    @Test (expected = FigureNotFoundException.class)
    public void whenFigureIsNotFound() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell cellSource = new Cell(2, 2);
        Bishop bishop = new Bishop(cellSource);
        board.add(bishop);
        board.move(new Cell(1, 1), new Cell(3, 3));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenImpossibleToMove() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell cellSource = new Cell(2, 2);
        Bishop bishop = new Bishop(cellSource);
        board.add(bishop);
        board.move(bishop.position, new Cell(3, 4));
    }

    @Test (expected = OccupiedWayException.class)
    public void whenOccupiedWay() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Bishop bishopOne = new Bishop(new Cell(2, 2));
        Bishop bishopTwo = new Bishop(new Cell(5, 5));
        board.add(bishopOne);
        board.add(bishopTwo);
        board.move(bishopOne.position, new Cell(6, 6));
    }



    @Test
    public void whenFigureDoesntExistInThreeThreeCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(2, 2)));
        assertThat(board.getFigureIndexByCell(new Cell(3, 3)), is(-1));
    }

    @Test
    public void whenFigureExistInFourSevenCell() {
        Board board = new Board();
        board.add(new Bishop(new Cell(4, 7)));
        assertThat(board.getFigureIndexByCell(new Cell(4, 7)), is(0));
    }
}
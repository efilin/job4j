package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class Board {

    private final int size = 8;

    private final ReentrantLock[][] board = new ReentrantLock[size][size];
    Random random = new Random();

    private ReentrantLock boardCell(Cell cell) {
        return this.board[cell.getX()][cell.getY()];
    }


    /**
     * Метод move() пробует залочить новую ячейку в течении 500мс,
     * если нет выдает false;
     *
     * @param source cell
     * @return
     */
    public boolean move(Cell source, Cell dest) {
        try {
            return boardCell(dest).tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Cell changePosition(Cell source) {
        Cell result;
        do {
            result = new Cell(random.nextInt(size), random.nextInt(size));
        }
        while (!result.equals(source));
        return result;
    }

    private Cell startPosition() {
        Cell result;
        do {
            result = new Cell(random.nextInt(size), random.nextInt(size));
        }
        while (boardCell(result).tryLock());
        return result;
    }


    Thread heroOne = new Thread(new Runnable() {
        @Override
        public void run() {
            Cell startCell = startPosition();
            Cell nextCell;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    nextCell = changePosition(startCell);
                    if (move(startCell, nextCell)) {
                        boardCell(startCell).unlock();
                        startCell = nextCell;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    });

    Thread heroTwo = new Thread(new Runnable() {
        @Override
        public void run() {
            Cell startCell = startPosition();
            Cell nextCell;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    nextCell = changePosition(startCell);
                    if (move(startCell, nextCell)) {
                        boardCell(startCell).unlock();
                        startCell = nextCell;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

        }
    });


}

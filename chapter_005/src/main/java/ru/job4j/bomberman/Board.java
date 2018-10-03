package ru.job4j.bomberman;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class Board {

    private int size;
    private int monsterQuantity;

    private final ReentrantLock[][] board = new ReentrantLock[size][size];
    private Random random = new Random();

    private ExecutorService monsterPool = Executors.newFixedThreadPool(monsterQuantity);

    private List<ReentrantLock> blocks;

    private ReentrantLock boardCell(Cell cell) {
        return this.board[cell.getX()][cell.getY()];
    }

    public Board(int size, int monsterQuantity) {
        this.size = size;
        this.monsterQuantity = monsterQuantity;
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

    private void makeBlocks(int blockFillPercentage) {
        Cell result;
        int blocksQuantity = this.size*this.size*blockFillPercentage/100;
        for (int i = 0; i <blocksQuantity; i++) {
            do {
                result = new Cell(random.nextInt(size), random.nextInt(size));
            }
            while (boardCell(result).tryLock());
            this.blocks.add(boardCell(result));
        }
    }

    private void heroMovement() {
        //реализовать движение героя
        //
    }


    public void monsterMovement() {
        monsterPool.submit(new Runnable() {
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

    //TODO API bomberman

    //TODO блоки

    //ThreadPool для чудовищ

    //Переменный размер доски и количество чудовищ


    /*Thread hero = new Thread(new Runnable() {
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
    });*/


}

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

    private char input;

    private final ReentrantLock[][] board = new ReentrantLock[size][size];
    private Random random = new Random();

    private ExecutorService monsterPool = Executors.newFixedThreadPool(monsterQuantity);

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


    private Cell heroChangePosition(Cell source) throws InterruptedException {
        int x = 0;
        int y = 0;
        if (input == 'w' && (source.getY() + 1) < size) {
            y = 1;
        } else if (input == 's' && (source.getY() - 1) >= 0) {
            y = -1;
        } else if (input == 'a' && (source.getX() - 1) >= 0) {
            x = -1;
        } else if (input == 'd' && (source.getX() + 1) < size) {
            x = 1;
        } else {
            System.out.println("Некорректный ввод. Введите один из символов: w,a,s,d");
        }
        return new Cell(source.getX() + x, source.getY() + y);
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

    Thread hero = new Thread(new Runnable() {
        @Override
        public void run() {
            Cell startCell = startPosition();
            Cell nextCell;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    nextCell = heroChangePosition(startCell);
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

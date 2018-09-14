package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Board {

    private final int size = 8;

    ReentrantLock[][] board = new ReentrantLock[size][size];


    public boolean move(Cell source, Cell dest) {
        board[source.getX()][source.getY()].lock();  // block until condition holds
        try {
            board[dest.getX()][dest.getY()] = board[source.getX()][source.getY()];
            board[source.getX()][source.getY()] = null;
            // ... method body
        } finally {
            board[source.getX()][source.getY()].unlock();
        }

        return true;
    }


    Thread heroOne = new Thread(new Runnable() {
        @Override
        public void run() {
            board[0][0].lock();
            /*while (true) {


            }*/

        }
    });

    Thread heroTwo = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });


}

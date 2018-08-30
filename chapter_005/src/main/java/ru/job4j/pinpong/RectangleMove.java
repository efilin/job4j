package ru.job4j.pinpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int x = 1;
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + x);
            if (this.rect.getX() == 300) {
                x = -1;
            } else if (this.rect.getX() == 0) {
                x = 1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package ru.job4j.pseudo;

public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        new Paint().draw(new Square());
        new Paint().draw(new Triangle());
    }
}


package ru.job4j.tictactoe;

import java.util.Scanner;

public class Client {
    private static final String SIZE = "Введите размер поля:";
    private static final String WIN_LINE = "Введите длину линии для победы:";
    private static final String WHOS_FIRST = "Если хотите чтобы компьютер ходил первым, введите false";
    private int size = -1;
    private int winLine = -1;
    private boolean isHumanFirst = true;
    private Scanner scanner;


    public Client(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startQuestions() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(SIZE);
            String input = scanner.nextLine();
            this.size = Integer.parseInt(input);
            System.out.println(WIN_LINE);
            input = scanner.nextLine();
            this.winLine = Integer.parseInt(input);
            System.out.println(WHOS_FIRST);
            input = scanner.nextLine();
            this.isHumanFirst = Boolean.parseBoolean(input);
        } while (this.size == -1 && this.winLine == -1);
    }

    public void menu(UI ui) {
        ui.show();
    }


    public static void main(String[] args) {
        Client client = new Client(new Scanner(System.in));
        client.startQuestions();
        client.menu(new UI(client.isHumanFirst, new Logic(client.scanner, new Field(client.size), client.winLine)));
    }
}

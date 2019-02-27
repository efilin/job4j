package ru.job4j.socket;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new Socket("localhost", 5000)) {
            new Client(socket).start();
        }
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String question;
        String answer;
        do {
            question = console.nextLine();
            out.println(question);
            answer = in.readLine();
            while (!answer.isEmpty()) {
                System.out.println(answer);
                answer = in.readLine();
            }
        } while (!"exit".equals(question));
    }
}




package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Server {
    private final Socket socket;
    private Map<String, String> answers;

    public Server(Socket socket) {
        this.socket = socket;
        makeWordsMap();
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String question;
        do {
            System.out.println("wait command ...");
            question = in.readLine();
            System.out.println(question);
            if (answers.get(question) == null) {
                out.println("I don't understand you");
                out.println();
            } else {
                out.println(answers.get(question));
                out.println();
            }
        } while (!"exit".equals(question));
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(5000).accept()) {
            new Server(socket).start();
        }
    }

    private void makeWordsMap() {
        answers = new HashMap<>();
        answers.put("hi", "Hello, dear friend, I'm a oracle.");
        answers.put("how are you", "I'm fine, thanks");
        answers.put("hello", "Hello, dear friend, I'm a oracle.");
        answers.put("exit", "Oracle is closing");
    }
}

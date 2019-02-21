package ru.job4j.chat;

        /*Создать программу 'Консольный чат'. Пользователь вводит слово-фразу,
        программа берет случайную фразу из текстового файла и выводит в ответ.
        Программа замолкает если пользователь вводит слово «стоп»,
        при этом он может продолжать отправлять сообщения в чат.
        Если пользователь вводит слово «продолжить», программа снова начинает отвечать.
        При вводе слова «закончить» программа прекращает работу.
        Запись диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.*/


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {

    private static final Logger LOG = LogManager.getLogger(ConsoleChat.class.getName());
    private static final String TEXT_FILE_NAME = "textfileforchat.txt";
    private final static String STOP = "стоп";
    private final static String CONTINUE = "продолжить";
    private final static String FINISH = "закончить";
    private boolean chat = true;
    private boolean mute = false;
    private List<String> chatPhrases;


    public void init() throws IOException {

        System.out.println("Здравствуйте, вас приветствует консольный чат");
        LOG.info("Здравствуйте, вас приветствует консольный чат");
        System.out.println("Пишите фразы, нажимайте ввод, чат вам ответит");
        LOG.info("Пишите фразы, нажимайте ввод, чат вам ответит");
        System.out.println("Если напишите стоп, программа замолкает");
        LOG.info("Если напишите стоп, программа замолкает");
        System.out.println("Если напишите продолжить, программа продолжает работу");
        LOG.info("Если напишите продолжить, программа продолжает работу");
        System.out.println("Если напишите закончить, программа прекращает работу");
        LOG.info("Если напишите закончить, программа прекращает работу");

        getChatPhrases(TEXT_FILE_NAME);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (chat) {
                String s = br.readLine();
                LOG.info("Пользователь ввел:" + s);
                if (s.equalsIgnoreCase(STOP)) {
                    System.out.println("Программа замолкает");
                    LOG.info("Программа замолкает");
                    mute = true;
                } else if (s.equalsIgnoreCase(CONTINUE)) {
                    System.out.println("Программа снова отвечает на вопросы");
                    LOG.info("Программа снова отвечает на вопросы");
                    mute = false;
                } else if (s.equalsIgnoreCase(FINISH)) {
                    System.out.println("Чат прекращает работу");
                    LOG.info("Чат прекращает работу");
                    chat = false;
                } else if (!mute) {
                    answerToUser();
                }
            }
        }
    }


    private void answerToUser() {
        int rnd = (int) Math.round(Math.random() * (chatPhrases.size() - 1));
        String s = this.chatPhrases.get(rnd);
        LOG.info("Чат ответил:" + s);
        System.out.println(s);
    }

    private void getChatPhrases(String filename) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            this.chatPhrases = new ArrayList<>();
            String s;
            while ((s = br.readLine()) != null) {
                this.chatPhrases.add(s);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new ConsoleChat().init();
    }
}

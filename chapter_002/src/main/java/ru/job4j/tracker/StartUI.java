package ru.job4j.tracker;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class StartUI {

    private List<Integer> ranges = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
    /**
     * Константа меню для добавления новой заявки.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

    private final Consumer<String> output;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input ввод данных.
     */
    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select:", ranges));
        } while (!"y".equals(this.input.ask("Exit? y")));
    }


    /**
     * Запускт программы.
     *
     * @param args
     */

    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(
                        new ConsoleInput()
                ),
                new Tracker(), System.out::println
        ).init();
    }
}
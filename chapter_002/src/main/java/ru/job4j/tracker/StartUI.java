package ru.job4j.tracker;


/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class StartUI {
   /**
     * Константа меню для добавления новой заявки.
     */
   private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Select:"));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Exit? y")));
    }



    /**
     * Запускт программы.
     * @param args
     */

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
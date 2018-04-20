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
    private static final String ADD = "0";

    private static final String SHOW = "1";

    private static final String EDIT = "2";

    private static final String DELETE = "3";

    private static final String FINDID = "4";

    private static final String FINDNAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                //добавление заявки вынесено в отдельный метод.
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();

            } else if (EDIT.equals(answer)) {
                this.editItem();

            } else if (DELETE.equals(answer)) {
                this.deleteItem();

            } else if (FINDID.equals(answer)) {
                this.findId();

            } else if (FINDNAME.equals(answer)) {
                this.findName();

            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, 1L);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    private void showAllItems() {
        System.out.println("------------ Отображение всех записей --------------");
        for (Item item: this.tracker.findAll()) {
            System.out.println("----------- Заявка id : " + item.getId() + "---------------");
            System.out.println("----------- Имя : " + item.getName() + "----------------");
            System.out.println("----------- Описание : " + item.getDescription() + "---------------");
        }
    }

    private void editItem() {
        System.out.println("------------ Редактирование записи --------------");
        String id = this.input.ask("Введение Id заявки:");
        String name = this.input.ask("Введите новое имя заявки :");
        String desc = this.input.ask("Введите новое описание заявки :");
        Item item = new Item(name, desc, 1L);
        this.tracker.replace(id, item);
    }

    private void deleteItem() {
        System.out.println("------------ Удаление записи --------------");
        String id = this.input.ask("Введение Id заявки:");
        this.tracker.delete(id);
        System.out.println("------------ Запись удалена --------------");
    }

    private void findId() {
        System.out.println("------------ Поиск записи по id --------------");
        String id = this.input.ask("Введение Id записи:");
        System.out.println("----------- Заявка id : " + this.tracker.findById(id).getId() + "---------------");
        System.out.println("----------- Имя : " + this.tracker.findById(id).getName() + "----------------");
        System.out.println("----------- Описание : " + this.tracker.findById(id).getDescription() + "---------------");
    }

    private void findName() {
        System.out.println("------------ Поиск записи по имени --------------");
        String id = this.input.ask("Введение имени записи:");
        this.tracker.findByName(id);
        for (Item item: this.tracker.findByName(id)) {
            System.out.println("----------- Заявка id : " + item.getId() + "---------------");
            System.out.println("----------- Имя : " + item.getName() + "----------------");
            System.out.println("----------- Описание : " + item.getDescription() + "---------------");
        }
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить заявку");
        System.out.println("1. Показать все записи");
        System.out.println("2. Изменить заявку по id");
        System.out.println("3. Удалить заявку по id");
        System.out.println("4. Найти заявку по id");
        System.out.println("5. Найти все заявки по имени");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
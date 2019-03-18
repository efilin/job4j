package ru.job4j.isp;

                /*
                1. Реализовать структуру для поддержания меню.
                2. Каждый элемент меню имеет имя. Все меню должно выводиться на экран.
                3. Каждый пункт меню может быть как одиночным элементов, так и иметь дочерние под пункты.
                4. Все меню должно выводиться на экран. В виде дерева.
                5. Предусмотреть возможность определять действие, когда пользователь выбрал конкретный пункт меню.
                Например
                Задача 1.
                ---- Задача 1.1.
                --------- Задача 1.1.1.
                --------- Задача 1.1.2.
                ----- Задача 1.2.*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private List<Element> rootElements;
    private Map<String, Element> map = new HashMap<>();
    public static final String DASH_SEPARATOR = "----";

    public Map<String, Element> getMap() {
        return map;
    }

    public void recursiveWalkTree(Element element) {
        if (element.getChildrens().size() != 0) {
            for (Element e : element.getChildrens()) {
                System.out.print(DASH_SEPARATOR.repeat(e.getDepth()));
                System.out.println(e.getName());
                recursiveWalkTree(e);
            }
        }
    }

    public void showMenu() {
        for (Element element : rootElements) {
            System.out.println(element.getName());
            recursiveWalkTree(element);
        }
        System.out.println("Введите номер задачи.");
        System.out.println("Для выхода введите exit");
    }


    public void init() {
        Element e1 = new Element("Task 1", "Do Task 1");
        Element e2 = new Element("Task 2", "Do Task 2");
        Element e11 = new Element("Task 1.1", "Do Task 1.1", e1);
        Element e12 = new Element("Task 1.2", "Do Task 1.2", e1);
        Element e111 = new Element("Task 1.1.1", "Do Task 1.1.1", e11);
        this.rootElements = Arrays.asList(e1, e2);
        this.map.put(e1.getKey(), e1);
        this.map.put(e2.getKey(), e2);
        this.map.put(e11.getKey(), e11);
        this.map.put(e12.getKey(), e12);
        this.map.put(e111.getKey(), e111);
    }
}

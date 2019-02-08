package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставки использовать add(int index, E value)
     * <p>
     * Изменено на Stream API
     *
     * @param task задача
     */
    public void put(Task task) {
        /*for (Task t:tasks) {
            if (task.getPriority() >= t.getPriority()) {
                i++;
            } else {
                break;
            }
        }*/
        var i = (int) tasks.stream()
                .filter(t -> t.getPriority() < task.getPriority())
                .count();
        tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
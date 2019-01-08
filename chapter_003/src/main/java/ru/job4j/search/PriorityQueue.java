package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        //forEach tasks

        //tasks.stream().sorted(Comparator.comparingInt(Task::getPriority)).
        //tasks.stream().filter(t -> t.getPriority() > task.getPriority()).;
        //tasks.forEach(t->{t.getPriority()});
        int i = 0;
        for (Task t:tasks) {
            if (task.getPriority() >= t.getPriority()) {
                i++;
            } else {
                break;
            }
        }
        tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
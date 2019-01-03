package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class MenuTracker {
    private Input input;
    private ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private final Consumer<String> output;

    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }


    public void fillActions() {
        this.actions.add(new MenuTracker.AddItem(0, "Add the new item"));
        this.actions.add(new MenuTracker.ShowItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new MenuTracker.DeleteId(3, "Delete item by id"));
        this.actions.add(new MenuTracker.FindId(4, "Find item by id"));
        this.actions.add(new MenuTracker.FindName(5, "Find items by name"));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String name = input.ask("Enter name:");
            String desc = input.ask("Enter description:");
            tracker.add(new Item(name, desc, 3));
        }
    }

    class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            for (Item item : tracker.findAll()) {
                if (item != null) {
                    output.accept(String.format("Name: %s| Desc: %s| Id: %s",
                            item.getName(), item.getDescription(), item.getId()));
                }
            }
        }
    }


    class DeleteId extends BaseAction {

        public DeleteId(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            int id = Integer.parseInt(input.ask("Please, enter the task's id: "));
            tracker.delete(id);
        }

    }


    class FindId extends BaseAction {

        public FindId(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            int id = Integer.parseInt(input.ask("Please, enter the task's id: "));
            output.accept(String.format("Name: %s| Desc: %s| Id: %s",
                    tracker.findById(id).getName(), tracker.findById(id).getDescription(), tracker.findById(id).getId()));
        }

    }


    class FindName extends BaseAction {

        public FindName(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            String name = input.ask("Please, enter the task's name: ");
            for (Item item : tracker.findByName(name)) {
                output.accept(String.format("Name: %s| Desc: %s| Id: %s",
                        item.getName(), item.getDescription(), item.getId()));
            }
        }
    }


}


class EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, ITracker tracker) {
        int id = Integer.parseInt(input.ask("Please, enter the task's id: "));
        String name = input.ask("Please, enter the task's name: ");
        String desc = input.ask("Please, enter the task's desc: ");
        Item item = new Item(name, desc, 3);
        item.setId(id);
        tracker.replace(item.getId(), item);
    }

}

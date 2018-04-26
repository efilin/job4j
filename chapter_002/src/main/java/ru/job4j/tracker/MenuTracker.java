package ru.job4j.tracker;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */



public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void test() {

    }

    public void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteId();
        this.actions[4] = new FindId();
        this.actions[5] = new FindName();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the task's name: ");
            String desc = input.ask("Please, enter the task's desc: ");
            tracker.add(new Item(name, desc, 3));
        }

        public String info() {
                return String.format("%s. %s", this.key(), "Add the new item. ");
        }
    }


    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                if (item != null) {
                System.out.println(String.format("Name: %s| Desc: %s| Id: %s",
                        item.getName(), item.getDescription(), item.getId()));
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "show all item.");
        }

    }
    private class DeleteId implements UserAction {
        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the task's id: ");
            tracker.delete(id);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Delete item by id: ");
        }
    }

    private class FindId implements UserAction {
        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the task's id: ");
            System.out.println(String.format("Name: %s| Desc: %s| Id: %s",
                    tracker.findById(id).getName(), tracker.findById(id).getDescription(), tracker.findById(id).getId()));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id: ");
        }
    }
    private class FindName implements UserAction {
        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the task's name: ");
            for (Item item : tracker.findByName(name)) {
                System.out.println(String.format("Name: %s| Desc: %s| Id: %s",
                        item.getName(), item.getDescription(), item.getId()));
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name: ");
        }
    }
}

class EditItem implements UserAction {
    public int key() {
        return 2;
    }


    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, enter the task's id: ");
        String name = input.ask("Please, enter the task's name: ");
        String desc = input.ask("Please, enter the task's desc: ");
        Item item = new Item(name, desc, 3);
        item.setId(id);
        tracker.replace(item.getId(), item);
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Edit the new item. ");
    }
}

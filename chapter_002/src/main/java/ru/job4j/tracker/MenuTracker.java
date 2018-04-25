package ru.job4j.tracker;





public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[5];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void test() {

    }

    public void fillActtions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new MenuTracker.ShowItem();
        this.actions[2] = new EditItem();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action: this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }
    }

    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Please, enter the task's name: ");
        String desc = input.ask("Please, enter the task's desc: ");
        tracker.add(new Item(name, desc, 3));
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Add the new item. ");
    }
    private static class ShowItem implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("Name: %s| Desc: %s| Id: %s",
                        item.getName(), item.getDescription(), item.getId()));
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "show all item.");
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
        Item item = new Item(name, desc,3);
        item.setId(id);
        tracker.edit(item);
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Edit the new item. ") ;
    }
}

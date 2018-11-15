package ru.job4j.tracker;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Item {
    public int id;
    public String name;
    public String description;
    public long create;

    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public long getCreate() {
        return this.create;
    }
    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }
}

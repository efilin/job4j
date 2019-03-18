package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class Element implements Action {

    private String name;
    private final String taskInfo;
    private Element parent = null;
    private List<Element> childrens = new ArrayList<>();
    public static final String REGEX = "[^0-9,\\\\.]+";


    public Element(String name, String taskInfo) {
        this.name = name;
        this.taskInfo = taskInfo;
    }

    public Element(String name, String taskInfo, Element parent) {
        this.name = name;
        this.taskInfo = taskInfo;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public String getName() {
        return this.name;
    }

    public List<Element> getChildrens() {
        return this.childrens;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public void addChild(Element child) {
        child.setParent(this);
        this.childrens.add(child);
    }

    public Element getParent() {
        return parent;
    }

    public String getKey() {
        return this.name.replaceAll(REGEX, "").trim();
    }

    public int getDepth() {
        int result = 0;
        Element tempElement = this;
        while (tempElement.parent != null) {
            tempElement = tempElement.getParent();
            result++;
        }
        return result;
    }

    @Override
    public void action() {
        System.out.println(this.taskInfo);
    }
}

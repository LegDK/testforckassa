package com.test.org.testforckassa.models;



public class ListItem {
    private String head;
    private String description;
    private String id;

    public ListItem(String head, String description, String id) {
        this.head = head;
        this.description = description;
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}

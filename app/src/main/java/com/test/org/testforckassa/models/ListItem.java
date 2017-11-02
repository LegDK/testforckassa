package com.test.org.testforckassa.models;



public class ListItem {
    private String head;
    private String description;
    private Integer id;


    public ListItem(Integer id, String head, String description) {
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

    public Integer getId() {
        return id;
    }
}


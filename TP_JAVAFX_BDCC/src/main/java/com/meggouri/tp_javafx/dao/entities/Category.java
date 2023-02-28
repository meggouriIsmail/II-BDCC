package com.meggouri.tp_javafx.dao.entities;

import java.io.Serializable;

public class Category implements Serializable {
    private int Id;
    private String name;

    public Category() {
    }

    public Category(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

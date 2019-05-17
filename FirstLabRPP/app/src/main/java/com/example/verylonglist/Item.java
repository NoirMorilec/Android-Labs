package com.example.verylonglist;

public class Item {
    public int id;
    public String color;

    public Item() {
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

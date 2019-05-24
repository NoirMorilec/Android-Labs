package com.example.verylonglist;

public class Item {
    public int id;
    public String color;
    public int idImage;

    public Item() {
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String color, int idImage) {
        this.id = id;
        this.color = color;
        this.idImage = idImage;
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

    public int getIdImage() {
        return idImage;
    }
}

package com.amitchell05.cheesemvc.models;

public class Cheese {
    private String name;
    private String description;
    private int cheeseId;
    private static int nextId = 1;

    public Cheese(String name, String description) {
        // Call the default constructor for the given class
        this();
        this.name = name;
        this.description = description;
    }

    // Make no-arg constructor to use nextId to initialize the cheese ID field
    // Ensures it's unique for every single cheese object created
    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package com.amitchell05.cheesemvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Name must not be empty or be more than 15 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    @Size(min=1, max=5, message = "Rating must not be empty or be more than 5 characters long")
    private String rating;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public Cheese(String name, String description, String rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    // Make no-arg constructor to use nextId to initialize the cheese ID field
    // Ensures it's unique for every single cheese object created
    public Cheese() {

    }

    public int getId() {
        return id;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

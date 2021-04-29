package com.mock.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "category")
public class Category {
    @Id
    private String id;

    @NotEmpty(message = "Category name cannot be empty")
    private String name;

    public Category() {
    }

    public Category(String id, @NotEmpty(message = "Category name cannot be empty") String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

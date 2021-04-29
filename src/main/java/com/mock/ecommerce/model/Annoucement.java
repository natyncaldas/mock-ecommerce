package com.mock.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "annoucement")
public class Annoucement {
    @Id
    private String id;
    @NotEmpty(message = "Must provide an image")
    private String image;
    private String text;

    public Annoucement() {
    }

    public Annoucement(@NotEmpty(message = "Must provide an image") String image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.mock.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @NotEmpty(message = "Seller username cannot be empty")
    private String sellerUsername;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @DecimalMin(value = "0.1", message = "Price must be higher than $0.10")
    private float price;
    @NotEmpty(message = "Category must not bem empty")
    private String categoryId;
    private String description;
    @NotEmpty(message = "Must provide an image source")
    private String image;
    @LastModifiedDate
    private Date releaseDate;

    public Product() {
    }

    public Product(@NotEmpty(message = "Seller username cannot be empty") String sellerUsername, @NotEmpty(message = "Title cannot be empty") String title, @DecimalMin(value = "0.1", message = "Price must be higher than $0.10") float price, @NotEmpty(message = "Category must not bem empty") String categoryId, String description, @NotEmpty(message = "Must provide an image source") String image) {
        this.sellerUsername = sellerUsername;
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}

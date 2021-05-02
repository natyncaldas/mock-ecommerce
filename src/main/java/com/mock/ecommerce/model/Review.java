package com.mock.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Document(collection = "review")
public class Review {
    @Id
    private String id;
    @NotEmpty(message = "Comment cannot be empty")
    @Size(max = 250)
    private String text;
    @NotEmpty(message = "Must refer to a product")
    private String productId;
    @NotEmpty(message = "Must provide a rating")
    @Min(value = 0, message = "Minimum rate is 0")
    @Max(value = 5, message = "Maximum rate is 5")
    private float rating;
    private Date postedDate = Date.from(Instant.now());

    public Review() {
    }

    public Review(String text, String productId, float rating) {
        this.text = text;
        this.productId = productId;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
}
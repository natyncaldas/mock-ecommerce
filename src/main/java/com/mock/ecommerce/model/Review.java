package com.mock.ecommerce.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @CreatedDate
    private Date postedDate;

    public Review() {
    }

    public Review(@NotEmpty(message = "Comment cannot be empty") @Size(max = 250) String text, @NotEmpty(message = "Must refer to a product") String productId) {
        this.text = text;
        this.productId = productId;
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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
}
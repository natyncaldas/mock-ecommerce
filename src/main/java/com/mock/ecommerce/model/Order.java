package com.mock.ecommerce.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Document(collection = "order")
public class Order {
    @Id
    private String id;
    @NotEmpty(message = "Must be associated with an user")
    private String userId;
    @NotNull(message = "Order must have at least (1) product")
    private Set<Product> products;
    @CreatedDate
    private Date date;

    public Order() {
    }

    public Order(@NotEmpty(message = "Must be associated with an user") String userId, @NotNull(message = "Order must have at least (1) product") Set<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

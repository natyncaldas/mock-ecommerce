package com.mock.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "seller_request")
public class SellerRequest {
    @Id
    private String id;
    @NotEmpty(message = "Must be associated with an user")
    @Indexed(unique = true)
    private String userId;

    public SellerRequest(){

    }

    public SellerRequest(String userId) {
        this.userId = userId;
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
}

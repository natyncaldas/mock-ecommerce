package com.mock.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Document(collection = "user")
public class User implements Serializable {
    @Id
    private String id;

    @NotBlank(message = "Username cannot be empty")
    @Size(max = 20, message = "Username must contain less than 20 characters")
    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50, message = "Email must contain less than 50 characters")
    @Email(message = "Must be a valid Email address")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 40, min = 6, message = "Password must contain between 6 and 40 characters")
    private String password;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    private String address;

    @NotNull(message = "Seller info cannot be null")
    private boolean isSeller;

    private Set<Role> roles = new HashSet<>();

    private Set<Product> cart = new HashSet<>();

    public User() {
    }

    public User(@NotBlank(message = "Username cannot be empty") @Size(max = 20, message = "Username must contain less than 20 characters") String username, @NotBlank(message = "Email cannot be empty") @Size(max = 50, message = "Email must contain less than 50 characters") @Email(message = "Must be a valid Email address") String email, @NotBlank(message = "Password cannot be empty") @Size(max = 40, min = 6, message = "Password must contain between 6 and 40 characters") String password, @NotBlank(message = "First name cannot be empty") String firstName, @NotBlank(message = "Last name cannot be empty") String lastName, String address, @NotNull(message = "Seller info cannot be null") boolean isSeller) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.isSeller = isSeller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(max = 20) String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(max = 40, min = 6) String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Product> getCart() {
        return cart;
    }

    public void setCart(Set<Product> cart) {
        this.cart = cart;
    }
}

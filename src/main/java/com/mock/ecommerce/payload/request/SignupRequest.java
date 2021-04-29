package com.mock.ecommerce.payload.request;

import com.mock.ecommerce.model.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


public class SignupRequest {
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

    private Set<String> roles = new HashSet<>();

    public SignupRequest() {
    }

    public SignupRequest(@NotBlank(message = "Username cannot be empty") @Size(max = 20, message = "Username must contain less than 20 characters") String username, @NotBlank(message = "Email cannot be empty") @Size(max = 50, message = "Email must contain less than 50 characters") @Email(message = "Must be a valid Email address") String email, @NotBlank(message = "Password cannot be empty") @Size(max = 40, min = 6, message = "Password must contain between 6 and 40 characters") String password, @NotBlank(message = "First name cannot be empty") String firstName, @NotBlank(message = "Last name cannot be empty") String lastName, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

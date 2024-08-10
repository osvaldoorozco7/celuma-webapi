package com.celuma.webapi.domain.request_models;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserRegistrationRequest {
    
    @NotNull(message = "First Name is a required field.")
    @NotBlank(message = "First Name must not be blank.")
    @NotEmpty(message = "First Name must not be empty.")
    private String firstName;
    
    @NotNull(message = "Last Name is a required field.")
    @NotBlank(message = "Last Name must not be blank.")
    @NotEmpty(message = "Last Name must not be empty.")
    private String lastName;
    
    @NotNull(message = "Username is a required field.")
    @NotBlank(message = "Username must not be blank.")
    @NotEmpty(message = "Username must not be empty.")
    private String username;
    
    @NotNull(message = "Email is a required field.")
    @NotBlank(message = "Email must not be blank.")
    @NotEmpty(message = "Email must not be empty.")
    @Email(message = "Please provide a valid email address.")
    private String email;
    
    @NotNull(message = "Password is a required field.")
    @NotBlank(message = "Password must not be blank.")
    @NotEmpty(message = "Password must not be empty.")
    @Length(min = 8)
    private String password;

    
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
}

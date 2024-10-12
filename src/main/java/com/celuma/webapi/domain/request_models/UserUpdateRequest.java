package com.celuma.webapi.domain.request_models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserUpdateRequest {

    private int userId;
    
    @NotNull(message = "First name is a required field.")
    @NotBlank(message = "First name must not be blank.")
    @NotEmpty(message = "First name must not be empty.")
    private String firstName;

    @NotNull(message = "Last name is a required field.")
    @NotBlank(message = "Last name must not be blank.")
    @NotEmpty(message = "Last name must not be empty.")
    private String lastName;

    @NotNull(message = "Email is a required field.")
    @NotBlank(message = "Email must not be blank.")
    @NotEmpty(message = "Email must not be empty.")
    private String email;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

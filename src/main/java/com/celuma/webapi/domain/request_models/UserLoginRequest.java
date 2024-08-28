package com.celuma.webapi.domain.request_models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserLoginRequest {

    @NotNull(message = "Username is a required field.")
    @NotBlank(message = "Username must not be blank.")
    @NotEmpty(message = "Username must not be empty.")
    private String username;

    @NotNull(message = "Password is a required field.")
    @NotBlank(message = "Password must not be blank.")
    @NotEmpty(message = "Password must not be empty.")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

package com.celuma.webapi.domain.request_models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserUpdatePasswordRequest {
    
    private int userId;
    
    @NotNull(message = "Current password is a required field.")
    @NotBlank(message = "Current password must not be blank.")
    @NotEmpty(message = "Current password must not be empty.")
    private String currentPassword;
    
    @NotNull(message = "New password is a required field.")
    @NotBlank(message = "New password must not be blank.")
    @NotEmpty(message = "New password must not be empty.")
    private String newPassword;
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getCurrentPassword() {
        return currentPassword;
    }
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

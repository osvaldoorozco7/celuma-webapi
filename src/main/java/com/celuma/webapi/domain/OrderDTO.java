package com.celuma.webapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class OrderDTO {


    private Integer orderId;

    private LocalDateTime creationDate;

    private boolean status;

    // Getters and Setters

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

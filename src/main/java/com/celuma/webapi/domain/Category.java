package com.celuma.webapi.domain;

import java.util.List;

public class Category {

    private Integer categoryId;
    private String description;
    private Boolean active;
    private List<ProductDTO> productDTOS;

    // Getters and Setters

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ProductDTO> getProducts() {
        return productDTOS;
    }

    public void setProducts(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}

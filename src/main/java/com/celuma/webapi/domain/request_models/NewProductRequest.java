package com.celuma.webapi.domain.request_models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class NewProductRequest {

    @Getter
    @Setter
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name must not be blank")
    @NotEmpty(message = "Name must not be empty")
    private String name;

    @Getter
    @Setter
    @NotNull(message = "Content is required")
    @NotBlank(message = "Content must not be blank")
    @NotEmpty(message = "Content must not be empty")
    private String content;

    @Getter
    @Setter
    @NotNull(message = "Category is required")
    @Min(value = 1, message = "Category must be a positive number")
    private int category;

    @Getter
    @Setter
    private  String instructions;

    @Getter
    @Setter
    private  String cautions;
}

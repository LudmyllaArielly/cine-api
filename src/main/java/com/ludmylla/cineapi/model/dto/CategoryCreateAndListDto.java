package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryCreateAndListDto {

    @NotBlank(message = "{required.category}")
    private String description;
}

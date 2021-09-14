package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdateDto {

    @NotNull(message = "{notnull.id}")
    private Long id;

    @NotBlank(message = "{required.category}")
    private String description;
}

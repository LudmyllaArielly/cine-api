package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StoryUpdateDto {

    @NotNull(message = "{notnull.id}")
    private Long id;

    @NotBlank(message = "{required.description}")
    private String description;
    private String image;
    private String audio;
    private CategoryCreateAndListDto categoryDto;
    private PeriodCreateAndListDto periodDto;

}

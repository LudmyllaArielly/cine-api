package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StoryUpdateDto {

    @NotBlank(message = "{required.description}")

    private Long id;
    private String description;
    private String image;
    private String audio;
    private CategoryCreateAndListDto categoryDto;
    private PeriodCreateAndListDto periodDto;

}

package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StoryUpdateDto {

    @NotBlank(message = "{required.description}")

    private Long id;
    private String description;
    private String image;
    private String audio;
    private Category category;
    private PeriodDto periodDto;

}

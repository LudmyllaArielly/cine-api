package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PeriodUpdateDto {

    @NotNull(message = "{notnull.id}")
    private Long id;

    @NotBlank(message = "{required.period}")
    private String periodOfStory;
}

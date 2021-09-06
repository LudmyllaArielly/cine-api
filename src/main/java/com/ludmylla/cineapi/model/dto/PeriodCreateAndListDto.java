package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PeriodCreateAndListDto {

    @NotBlank(message = "{required.period}")
    private String periodOfStory;
}

package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StoryCreateDtO {

    @NotBlank(message = "{required.description}")
    private String description;

    private String image;
    private String audio;

    private UserCpfDto userCpfDto;

    private CategoryCreateAndListDto categoryDto;

    private PeriodCreateAndListDto periodDto;

}

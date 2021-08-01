package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StoryCreateDtO {

    @NotBlank(message = "{required.description}")
    private String description;
    private String audio;
    private UserCpfDto userCpfDto;
    private Category category;
    private PeriodDto periodDto;

}

package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.Category;
import lombok.Data;

@Data
public class StoryCreateDtO {

    private String description;
    private String audio;
    private UserCpfDto userCpfDto;
    private Category category;
    private PeriodDto periodDto;

}

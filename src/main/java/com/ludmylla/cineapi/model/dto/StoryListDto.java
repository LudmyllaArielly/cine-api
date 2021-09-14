package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.StoryStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class StoryListDto {

    private String description;
    private String audio;
    private String image;
    private Instant moment;
    private CategoryCreateAndListDto categoryDto;
    private PeriodCreateAndListDto periodDto;
    private StoryStatus storyStatus;
    private UserListDto userListDto;
}

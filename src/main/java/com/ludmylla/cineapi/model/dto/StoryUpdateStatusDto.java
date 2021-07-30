package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.StoryStatus;
import lombok.Data;

@Data
public class StoryUpdateStatusDto {

    private Long id;
    private StoryStatus status;
}

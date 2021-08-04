package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.Category;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import lombok.Data;

import java.net.URI;
import java.time.Instant;

@Data
public class StoryListDto {

    private String description;
    private String audio;
    private URI image;
    private Instant moment;
    private Category category;
    private StoryStatus storyStatus;
    private UserListDto userListDto;
}

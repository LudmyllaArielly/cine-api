package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.Category;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.enums.StoryStatus;

import java.util.List;

public interface StoryService {

    void createStory(Story story);

    List<Story> findAll();

    List<Story> findStoryByPeriod(String periodOfStory);
    List<Story> findStoryByCategory(String description);

    List<Story> findStoryByStatus(StoryStatus storyStatus);

    void updateStoryStatus(Story story);

    void updateStory(Story story);

    void deleteStory(Long id);

}

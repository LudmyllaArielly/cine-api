package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.enums.Category;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.util.List;

public interface StoryService {

    void createStory(Story story, MultipartFile multipartFile) throws FilerException;

    List<Story> findAll();

    List<Story> findStoryByPeriod(String periodOfStory);

    List<Story> findStoryByCategory(Category category);

    List<Story> findStoryByStatus(StoryStatus storyStatus);

    void updateStoryStatus(Story story);

    void updateStory(Story story);

    void deleteStory(Long id);

}

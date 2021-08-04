package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.Story;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.net.URI;
import java.util.List;

public interface StoryService {

    void createStory(Story story, MultipartFile multipartFile) throws FilerException;

    List<Story> findAll();

    URI uploadStoryPicture(MultipartFile file) throws FilerException;


}

package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
public class StoryServiceImpl implements  StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public URI uploadStoryPicture(MultipartFile multipartFile) {
        return null;
    }
}

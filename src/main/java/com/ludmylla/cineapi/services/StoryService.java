package com.ludmylla.cineapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface StoryService {

    URI uploadStoryPicture(MultipartFile multipartFile);
}

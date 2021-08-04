package com.ludmylla.cineapi.services;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.net.URI;

public interface StoryService {

    URI uploadStoryPicture(MultipartFile file) throws FilerException;
}

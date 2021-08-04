package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.net.URI;

@RestController
@RequestMapping("/stories")
public class StoryResource {

    @Autowired
    private StoryService storyService;

    @PostMapping("/picture/{file}")
    public ResponseEntity<Void> uploadPicture(@RequestParam(name="file") MultipartFile file) throws FilerException {
        URI uri = storyService.uploadStoryPicture(file);
        return ResponseEntity.created(uri).build();

    }
}

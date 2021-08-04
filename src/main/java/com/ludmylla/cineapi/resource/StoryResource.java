package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.mapper.StoryMapper;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.dto.StoryCreateDtO;
import com.ludmylla.cineapi.model.dto.StoryListDto;
import com.ludmylla.cineapi.model.dto.UserCreateDto;
import com.ludmylla.cineapi.repository.StoryRepository;
import com.ludmylla.cineapi.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryResource {

    @Autowired
    private StoryService storyService;

    @Autowired
    private StoryRepository storyRepository;
/*
    @PostMapping("/picture/{file}")
    public ResponseEntity<Void> uploadPicture(@RequestParam(name="file") MultipartFile file) throws FilerException {
        URI uri = storyService.uploadStoryPicture(file);
        return ResponseEntity.created(uri).build();

    }*/

    @PostMapping("/{file}")
    public ResponseEntity<?> createStory(@RequestPart(name="file") MultipartFile file,
                                         @ModelAttribute StoryCreateDtO storyCreateDtO) throws FilerException {
        Story story = StoryMapper.INSTANCE.toStory(storyCreateDtO);
        storyService.createStory(story, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<StoryListDto>> findAll() {
        List<Story> story = storyRepository.findAll();
        List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
        return ResponseEntity.ok(storyListDtos);
    }


}

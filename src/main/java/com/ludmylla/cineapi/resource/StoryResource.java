package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.mapper.StoryMapper;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.dto.StoryCreateDtO;
import com.ludmylla.cineapi.model.dto.StoryListDto;
import com.ludmylla.cineapi.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryResource {

    @Autowired
    private StoryService storyService;

    @PostMapping("/{file}")
    public ResponseEntity<?> createStory(@RequestPart(name="file") MultipartFile file,
                                         @ModelAttribute StoryCreateDtO storyCreateDtO) throws FilerException {
        Story story = StoryMapper.INSTANCE.toStory(storyCreateDtO);
        storyService.createStory(story, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<StoryListDto>> findAll() {
        List<Story> story = storyService.findAll();
        List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
        return ResponseEntity.ok(storyListDtos);
    }


}

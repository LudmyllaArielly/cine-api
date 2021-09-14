package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.mapper.StoryMapper;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.dto.StoryCreateDtO;
import com.ludmylla.cineapi.model.dto.StoryListDto;
import com.ludmylla.cineapi.model.dto.StoryUpdateDto;
import com.ludmylla.cineapi.model.dto.StoryUpdateStatusDto;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import com.ludmylla.cineapi.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryResource {

    @Autowired
    private StoryService storyService;

    @PostMapping()
    public ResponseEntity<?> createStory(@Valid @RequestBody StoryCreateDtO storyCreateDtO)  {
        Story story = StoryMapper.INSTANCE.toStory(storyCreateDtO);
        storyService.createStory(story);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<StoryListDto>> findAll() {
        List<Story> story = storyService.findAll();
        List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
        return ResponseEntity.ok(storyListDtos);
    }

    @GetMapping("/findStoryByPeriod")
    public ResponseEntity<List<StoryListDto>> findStoryByPeriod(@RequestParam("periodOfStory") String periodOfStory){
        List<Story> story = storyService.findStoryByPeriod(periodOfStory);
        List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
        return ResponseEntity.ok(storyListDtos);
    }

    @GetMapping("/findStoryByCategory")
    public ResponseEntity<List<StoryListDto>> findStoryByCategory(@RequestParam("description") String description) {
        List<Story> story = storyService.findStoryByCategory(description);
        List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
        return ResponseEntity.ok(storyListDtos);
    }

    @GetMapping("/findStoryByStatus")
    public ResponseEntity<List<StoryListDto>> findStoryByStatus(@RequestParam("status") StoryStatus status){
        List<Story> story = storyService.findStoryByStatus(status);
        List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
        return ResponseEntity.ok(storyListDtos);
    }

    @PatchMapping("/updateStoryStatus")
    public ResponseEntity<?> updateStoryStatus(@RequestBody StoryUpdateStatusDto storyUpdateStatusDto){
           Story story = StoryMapper.INSTANCE.toStory(storyUpdateStatusDto);
           storyService.updateStoryStatus(story);
           return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateStory (@Valid @RequestBody StoryUpdateDto storyUpdateDto){
        Story story = StoryMapper.INSTANCE.toStory(storyUpdateDto);
        storyService.updateStory(story);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable("id") Long id){
        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }

}

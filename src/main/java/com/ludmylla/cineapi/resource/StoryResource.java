package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.exceptions.StoryNotFoundException;
import com.ludmylla.cineapi.mapper.StoryMapper;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.dto.StoryCreateDtO;
import com.ludmylla.cineapi.model.dto.StoryListDto;
import com.ludmylla.cineapi.model.dto.StoryUpdateStatusDto;
import com.ludmylla.cineapi.model.enums.Category;
import com.ludmylla.cineapi.model.enums.StoryStatus;
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

    @GetMapping("/findStoryByPeriod/{periodOfStory}")
    public ResponseEntity<List<StoryListDto>> findStoryByPeriod(@PathVariable("periodOfStory") String periodOfStory) throws StoryNotFoundException {
        try{
            List<Story> story = storyService.findStoryByPeriod(periodOfStory);
            List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
            return ResponseEntity.ok(storyListDtos);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findStoryByCategory/{category}")
    public ResponseEntity<List<StoryListDto>> findStoryByCategory(@PathVariable("category")Category category) throws StoryNotFoundException{
        try{
            List<Story> story = storyService.findStoryByCategory(category);
            List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
            return ResponseEntity.ok(storyListDtos);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findStoryByStatus/{status}")
    public ResponseEntity<List<StoryListDto>> findStoryByStatus(@PathVariable("status") StoryStatus status) throws  StoryNotFoundException{
        try{
            List<Story> story = storyService.findStoryByStatus(status);
            List<StoryListDto> storyListDtos = StoryMapper.INSTANCE.toListDto(story);
            return ResponseEntity.ok(storyListDtos);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/updateStoryStatus")
    public ResponseEntity<?> updateStoryStatus(@RequestBody StoryUpdateStatusDto storyUpdateStatusDto){
        try{
            Story story = StoryMapper.INSTANCE.toStory(storyUpdateStatusDto);
            storyService.updateStoryStatus(story);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}

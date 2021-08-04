package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.enums.Category;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import com.ludmylla.cineapi.repository.PeriodRepository;
import com.ludmylla.cineapi.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements  StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix}")
    private String prefix;

    @Value("${img.size}")
    private Integer size;

    @Override
    public void createStory(Story story, MultipartFile file) throws FilerException {
        getUserCpf(story);
        getPeriodStory(story);
        story.setImage(uploadStoryPicture(file));
        story.setStoryStatus(StoryStatus.CREATED);
        story.setMoment(Instant.now());
        System.out.println(story.getImage());
        storyRepository.save(story);
    }

    @Override
    public List<Story> findAll(){
        return storyRepository.findAll();
    }

    @Override
    public List<Story> findStoryByPeriod(String periodOfStory){
        List<Story> story = storyRepository.findStoryByPeriod(periodOfStory);
        validStoryExist(story);
        return story;
    }

    @Override
    public List<Story> findStoryByCategory(Category category){
        List<Story> story = storyRepository.findStoryByCategory(category);
        validStoryExist(story);
        return story;
    }

    @Override
    public List<Story> findStoryByStatus(StoryStatus storyStatus){
        List<Story> story = storyRepository.findAll();
        validStoryExist(story);
        return story.stream()
                .filter(s -> s.getStoryStatus().equals(storyStatus))
                .collect(Collectors.toList());
    }

    @Override
    public URI uploadStoryPicture(MultipartFile file) throws FilerException {
        BufferedImage jpgImage = imageService.getJpaImageFromFile(file);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = file.getName() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }

    private Story getUserCpf(Story story){
        User user = userService.findByCpf(story.getUser().getCpf());
        validUserExist(user);
        story.setUser(user);
        return story;
    }

    private Story getPeriodStory(Story story){
        Period period = periodRepository.findByPeriodOfStory(story.getPeriod().getPeriodOfStory());
        validPeriodExist(period);
        story.setPeriod(period);
        return story;
    }

    private void validStoryExist(List<Story> story){
        if(story == null){
            throw new IllegalArgumentException("Story does not exist");
        }
    }

    private void validUserExist(User user){
        if(user == null){
            throw new IllegalArgumentException("User does not exist");
        }
    }

    private void validPeriodExist(Period period){
        if(period == null){
            throw new IllegalArgumentException("Period does not exist");
        }
    }
}

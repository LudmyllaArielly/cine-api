package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.exceptions.StoryNotFoundException;
import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.enums.Category;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import com.ludmylla.cineapi.repository.PeriodRepository;
import com.ludmylla.cineapi.repository.StoryRepository;
import com.ludmylla.cineapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
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

    @Value("${img.size}")
    private Integer size;

    @Override
    public void createStory(Story story, MultipartFile file) throws FilerException {
        validationsCreateStory(story);
        story.setImage(uploadStoryPicture(file));
        System.out.println(story.getImage());
        storyRepository.save(story);
    }

    @Override
    public List<Story> findAll(){
        return storyRepository.findAll();
    }

    @Override
    public List<Story> findStoryByPeriod(String periodOfStory) throws StoryNotFoundException{
        List<Story> story = storyRepository.findStoryByPeriod(periodOfStory);
        validStoryExist(story);
        return story;
    }

    @Override
    public List<Story> findStoryByCategory(Category category) throws StoryNotFoundException{
        List<Story> story = storyRepository.findStoryByCategory(category);
        validStoryExist(story);
        return story;
    }

    @Override
    public List<Story> findStoryByStatus(StoryStatus storyStatus) throws StoryNotFoundException{
        List<Story> story = storyRepository.findAll();
        validStoryExist(story);
        return story.stream()
                .filter(s -> s.getStoryStatus().equals(storyStatus))
                .collect(Collectors.toList());
    }

    @Modifying
    @Transactional
    @Override
    public void updateStoryStatus(Story story) throws StoryNotFoundException{
        validationsUpdateStatus(story);
        storyRepository.save(story);
    }

    @Modifying
    @Transactional
    @Override
    public void updateStory(Story story) throws StoryNotFoundException {
        validationsUpdateStory(story);
        storyRepository.save(story);
    }

    @Override
    public void deleteStory(Long id){
        Optional<Story> story = storyRepository.findById(id);
        Story stories = story.get();
        storyRepository.delete(stories);
    }

    @Override
    public URI uploadStoryPicture(MultipartFile file) throws FilerException {
        BufferedImage jpgImage = imageService.getJpaImageFromFile(file);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = file.getOriginalFilename() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }

    private void validationsUpdateStatus(Story story) throws StoryNotFoundException{
        getStory(story);
        validUpdateStoryStatus(story);
    }

    private void validationsCreateStory(Story story){
        getUserCpf(story);
        getPeriodStory(story);
        setStory(story);
    }

    private void validationsUpdateStory(Story story){
        getStoryUpdate(story);
        getPeriodStory(story);
    }

    private Story getUserCpf(Story story){
        User user = userService.findByCpf(story.getUser().getCpf());
        userService.validUserExist(user);
        story.setUser(user);
        return story;
    }

    private Story getPeriodStory(Story story){
        Period period = periodRepository.findByPeriodOfStory(story.getPeriod().getPeriodOfStory());
        validPeriodExist(period);
        story.setPeriod(period);
        return story;
    }

    private void setStory(Story story){
        story.setStoryStatus(StoryStatus.CREATED);
        story.setMoment(Instant.now());
    }

    private Story findById(Long id) throws StoryNotFoundException{
        return storyRepository.findById(id)
                .orElseThrow(() -> new StoryNotFoundException("Story does not exist."));
    }

    private void validStoryExist(List<Story> story){
        if(story == null){
            throw new StoryNotFoundException("Story does not exist");
        }
    }

    private void validPeriodExist(Period period){
        if(period == null){
            throw new IllegalArgumentException("Period does not exist");
        }
    }

    private void getStoryUpdate(Story story) throws StoryNotFoundException{
        Story storyData = findById(story.getId());
        story.setStoryStatus(storyData.getStoryStatus());
        story.setMoment(storyData.getMoment());
        story.setUser(storyData.getUser());
    }

    private Story getStory(Story story) throws StoryNotFoundException{
        Story storyGetData = findById(story.getId());
        story.setCategory(storyGetData.getCategory());
        story.setPeriod(storyGetData.getPeriod());
        story.setMoment(storyGetData.getMoment());
        story.setDescription(storyGetData.getDescription());
        story.setImage(storyGetData.getImage());
        story.setAudio(storyGetData.getAudio());
        story.setUser(storyGetData.getUser());
        return story;
    }

    private void validUpdateStoryStatus(Story story) {
        Story stories = findById(story.getId());
        Boolean storyIsCreated = stories.getStoryStatus().equals(StoryStatus.CREATED);
        Boolean storyIsApproved = stories.getStoryStatus().equals(StoryStatus.APPROVED);

        if(storyIsCreated) {
            if(Utils.storyIsDifferentFromApprovedOrNotApproved(story)) {
                throw new IllegalArgumentException("You cannot cancel or create an already created story. Available options: APPROVED, NOT_APPROVED");
            }
        }else if(storyIsApproved) {
            if(Utils.storyIsDiferrentCFromCanceled(story)) {
                throw new IllegalArgumentException("An approved story can only be canceled. Available options: CANCELED");
            }
        }
    }
}

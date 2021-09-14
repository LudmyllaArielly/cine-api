package com.ludmylla.cineapi.services.impl;

import com.ludmylla.cineapi.exceptions.*;
import com.ludmylla.cineapi.model.Category;
import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import com.ludmylla.cineapi.repository.CategoryRepository;
import com.ludmylla.cineapi.repository.PeriodRepository;
import com.ludmylla.cineapi.repository.StoryRepository;
import com.ludmylla.cineapi.services.CategoryService;
import com.ludmylla.cineapi.services.PeriodService;
import com.ludmylla.cineapi.services.StoryService;
import com.ludmylla.cineapi.services.UserService;
import com.ludmylla.cineapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void createStory(Story story) {
        validationsCreateStory(story);
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
    public void updateStoryStatus(Story story) throws StoryWithWrongStatusException, StoryNotFoundException{
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
    public void deleteStory(Long id) throws StoryNotFoundException{
        Story story = findById(id);
        verifyIfStoryExists(story);
        storyRepository.delete(story);
    }

    private void validationsUpdateStatus(Story story) throws StoryNotFoundException, StoryWithWrongStatusException{
        verifyIfStoryExists(story);
        validUpdateStoryStatus(story);
        getStory(story);
    }

    private void validationsCreateStory(Story story){
        getUserCpf(story);
        getPeriodStory(story);
        getCategoryStory(story);
        setStory(story);
    }

    private void validationsUpdateStory(Story story) {
        verifyIfStoryExists(story);
        getStoryUpdate(story);
        getPeriodStory(story);
        getCategoryStory(story);
    }

    private Story getUserCpf(Story story) throws UserNotFoundException{
        User user = userService.findByCpf(story.getUser().getCpf());
        story.setUser(user);
        return story;
    }

    private Story getCategoryStory(Story story) throws CategoryNotFoundException{
        Category category = categoryService.findByCategory(story.getCategory().getDescription());
        story.setCategory(category);
        return story;
    }

    private Story getPeriodStory(Story story) throws PeriodNotFoundException{
        Period period = periodService.findByPeriod(story.getPeriod().getPeriodOfStory());
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

    private void getStoryUpdate(Story story) throws StoryNotFoundException{
        Story storyData = findById(story.getId());
        story.setStoryStatus(storyData.getStoryStatus());
        story.setMoment(storyData.getMoment());
        story.setUser(storyData.getUser());
    }

    private void verifyIfStoryExists(Story story) throws StoryNotFoundException{
        Story storyId = findById(story.getId());
        if(story == null){
            throw new StoryNotFoundException("Story does not exist.");
        }
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

    private void validUpdateStoryStatus(Story story) throws StoryWithWrongStatusException {
        Story stories = findById(story.getId());
        Boolean storyIsCreated = stories.getStoryStatus().equals(StoryStatus.CREATED);
        Boolean storyIsApproved = stories.getStoryStatus().equals(StoryStatus.APPROVED);

        if(storyIsCreated) {
            if(Utils.storyIsDifferentFromApprovedOrNotApproved(story)) {
                throw new StoryWithWrongStatusException("You cannot cancel or create an already created story. Available options: APPROVED, NOT_APPROVED");
            }
        }else if(storyIsApproved) {
            if(Utils.storyIsDiferrentCFromCanceled(story)) {
                throw new StoryWithWrongStatusException("An approved story can only be canceled. Available options: CANCELED");
            }
        }
    }
}

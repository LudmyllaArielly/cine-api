package com.ludmylla.cineapi.utils;

import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.enums.StoryStatus;

public class Utils {

    public static Boolean storyIsDifferentFromApprovedOrNotApproved(Story story) {
        return !story.getStoryStatus().equals(StoryStatus.APPROVED) &&
                !story.getStoryStatus().equals(StoryStatus.NOT_APPROVED);
    }

    public static Boolean storyIsDiferrentCFromCanceled(Story story) {
        return !story.getStoryStatus().equals(StoryStatus.CANCELED);
    }
}

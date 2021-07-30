package com.ludmylla.cineapi.repository;

import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query(value = "select s.*, p.period_of_story from story as s join period as p on s.period_id=p.id " +
            "where p.period_of_story= :periodOfStory group by s.id order by s.moment asc", nativeQuery = true)
    List<Story> findStoryByPeriod(@Param("periodOfStory") String periodOfStory);

    @Query("select u from Story u where u.category= :category")
    List<Story> findStoryByCategory(@Param("category") Category category);

}

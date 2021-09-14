package com.ludmylla.cineapi.repository;

import com.ludmylla.cineapi.model.Category;
import com.ludmylla.cineapi.model.Story;
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

    @Query(value = "select s.*, c.description from Story as s join Category as c " +
            "on s.category_id = c.id where c.description = :description " +
            "group by s.id order by s.moment asc", nativeQuery = true)
    List<Story> findStoryByCategory(@Param("description") String description);

}

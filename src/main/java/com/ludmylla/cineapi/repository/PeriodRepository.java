package com.ludmylla.cineapi.repository;

import com.ludmylla.cineapi.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    Period findByPeriodOfStory (String periodOfStory );
}

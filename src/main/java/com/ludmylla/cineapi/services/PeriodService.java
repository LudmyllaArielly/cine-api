package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.Period;

import java.util.List;

public interface PeriodService {

    Long createPeriod(Period period);

    List<Period> findAll();

    Period findById(Long id);

    void updatePeriod(Period period);

    void deletePeriod(Long id);
}

package com.ludmylla.cineapi.services.impl;

import com.ludmylla.cineapi.exceptions.PeriodNotFoundException;
import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.repository.PeriodRepository;
import com.ludmylla.cineapi.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public Long createPeriod(Period period) {
        Period periodSaved = periodRepository.save(period);
        return periodSaved.getId();
    }

    @Override
    public List<Period> findAll() {
        return periodRepository.findAll();
    }

    @Override
    public Period findByPeriod(String periodOfStory) throws PeriodNotFoundException{
        Period period = periodRepository.findByPeriodOfStory(periodOfStory);
        verifyIfPeriodExists(period);
        return period;
    }

    @Override
    public Period findById(Long id) throws PeriodNotFoundException {
        return periodRepository.findById(id)
                .orElseThrow(() -> new PeriodNotFoundException("Period does not exist"));
    }

    @Override
    public void updatePeriod(Period period) throws PeriodNotFoundException{
        Period periodExist = findById(period.getId());
        verifyIfPeriodExists(period);
        periodRepository.save(period);
    }

    @Override
    public void deletePeriod(Long id) throws PeriodNotFoundException{
        Period period = findById(id);
        verifyIfPeriodExists(period);
        periodRepository.delete(period);
    }

    private void verifyIfPeriodExists(Period period){
        if(period == null){
            throw new PeriodNotFoundException("Period does not exist");
        }
    }

}

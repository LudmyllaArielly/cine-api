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
    public Period findById(Long id) throws PeriodNotFoundException {
        return periodRepository.findById(id)
                .orElseThrow(() -> new PeriodNotFoundException("Period does not exist"));
    }

    @Override
    public void updatePeriod(Period period) throws PeriodNotFoundException{
        verifyIfPeriodExists(period.getId());
        Period periodExist = findById(period.getId());
        periodRepository.save(period);
    }

    @Override
    public void deletePeriod(Long id) {
        verifyIfPeriodExists(id);
        Period period = findById(id);
        periodRepository.delete(period);

    }

    private void verifyIfPeriodExists(Long id){
        Period period = findById(id);
        if(period == null){
            throw new PeriodNotFoundException("Period does not exist");
        }
    }

}

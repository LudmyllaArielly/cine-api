package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.exceptions.PeriodNotFoundException;
import com.ludmylla.cineapi.mapper.PeriodMapper;
import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.model.dto.PeriodCreateAndListDto;
import com.ludmylla.cineapi.model.dto.PeriodUpdateDto;
import com.ludmylla.cineapi.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/periods")
public class PeriodResource {

    @Autowired
    private PeriodService periodService;

    @PostMapping
    public ResponseEntity<?> createPeriod(@Valid @RequestBody PeriodCreateAndListDto periodCreateAndListDto){
        try{
            Period period = PeriodMapper.INSTANCE.toPeriod(periodCreateAndListDto);
            periodService.createPeriod(period);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PeriodCreateAndListDto>> findAll(){
        List<Period> list = periodService.findAll();
        List<PeriodCreateAndListDto> periodDto = PeriodMapper.INSTANCE.toListDto(list);
        return ResponseEntity.ok(periodDto);
    }

    @GetMapping("/{periodOfStory}")
    public ResponseEntity<?> findByPeriod(@RequestParam("periodOfStory") String periodOfStory){
        Period period = periodService.findByPeriod(periodOfStory);
        PeriodCreateAndListDto periodCreateAndListDto = PeriodMapper.INSTANCE.toDto(period);
        return ResponseEntity.ok(periodCreateAndListDto);
    }

    @PutMapping
    public ResponseEntity<?> updatePeriod(@Valid @RequestBody PeriodUpdateDto periodUpdateDto){
        Period period = PeriodMapper.INSTANCE.toPeriod(periodUpdateDto);
        periodService.updatePeriod(period);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeriod(@PathVariable("id") Long id){
        periodService.deletePeriod(id);
        return ResponseEntity.noContent().build();
    }
}

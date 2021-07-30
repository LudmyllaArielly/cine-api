package com.ludmylla.cineapi.mapper;

import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.model.dto.PeriodDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeriodMapper {

    PeriodMapper INSTANCE = Mappers.getMapper(PeriodMapper.class);

    @Mapping(target = "id", ignore = true)
    Period toPeriod (PeriodDto source);
}

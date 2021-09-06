package com.ludmylla.cineapi.mapper;

import com.ludmylla.cineapi.model.Period;
import com.ludmylla.cineapi.model.dto.PeriodCreateAndListDto;
import com.ludmylla.cineapi.model.dto.PeriodUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PeriodMapper {

    PeriodMapper INSTANCE = Mappers.getMapper(PeriodMapper.class);

    @Mapping(target = "id", ignore = true)
    Period toPeriod (PeriodCreateAndListDto source);

    PeriodCreateAndListDto toDto(Period source);

    List<PeriodCreateAndListDto> toListDto (List<Period> source);

    Period toPeriod(PeriodUpdateDto source);
}

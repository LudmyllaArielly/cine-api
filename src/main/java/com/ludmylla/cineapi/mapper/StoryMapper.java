package com.ludmylla.cineapi.mapper;

import com.ludmylla.cineapi.model.Story;
import com.ludmylla.cineapi.model.dto.StoryCreateDtO;
import com.ludmylla.cineapi.model.dto.StoryListDto;
import com.ludmylla.cineapi.model.dto.StoryUpdateStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, PeriodMapper.class})
public interface StoryMapper {


    StoryMapper INSTANCE = Mappers.getMapper(StoryMapper.class);

    @Mapping( target = "id", ignore = true)
    @Mapping( target = "storyStatus", ignore = true)
    @Mapping( target = "period", source="periodDto")
    @Mapping( target = "image", ignore = true)
    @Mapping( target = "moment", ignore = true)
    @Mapping( target = "user", source = "userCpfDto")
    Story toStory (StoryCreateDtO source);

    @Mapping( target = "id", ignore = true)
    @Mapping( target = "period", ignore = true)
    @Mapping( target = "user", source = "userListDto")
    Story toStory (StoryListDto source);

    @Mapping( target = "userListDto", source = "user")
    StoryListDto toDto (Story source);

    List<StoryListDto> toListDto (List<Story> source);

    @Mapping( target = "audio", ignore = true)
    @Mapping( target = "storyStatus", source= "status")
    @Mapping( target = "period", ignore = true)
    @Mapping( target = "category", ignore = true)
    @Mapping( target = "description", ignore = true)
    @Mapping( target = "image", ignore = true)
    @Mapping( target = "moment", ignore = true)
    @Mapping( target = "user", ignore = true)
    Story toStory (StoryUpdateStatusDto source);

}

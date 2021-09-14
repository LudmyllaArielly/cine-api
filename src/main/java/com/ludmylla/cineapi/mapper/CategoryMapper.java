package com.ludmylla.cineapi.mapper;

import com.ludmylla.cineapi.model.Category;
import com.ludmylla.cineapi.model.dto.CategoryCreateAndListDto;
import com.ludmylla.cineapi.model.dto.CategoryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    Category toCategory (CategoryCreateAndListDto source);

    CategoryCreateAndListDto toDto(Category source);

    List<CategoryCreateAndListDto> toListDto(List<Category> source);

    Category toCategory (CategoryUpdateDto source);
}

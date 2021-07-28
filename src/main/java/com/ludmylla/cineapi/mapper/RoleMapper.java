package com.ludmylla.cineapi.mapper;

import com.ludmylla.cineapi.model.Role;
import com.ludmylla.cineapi.model.dto.RoleCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id", ignore = true)
    Role toRole(RoleCreateDto source);

    RoleCreateDto toRoleDto(Role source);

    @Mapping(target = "id", ignore = true)
    List<RoleCreateDto> toRoleListDto(List<Role> source);

}
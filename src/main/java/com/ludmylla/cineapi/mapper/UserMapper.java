package com.ludmylla.cineapi.mapper;

import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { RoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", source = "roleCreateDtos")
    User toUser(UserListDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", source = "roleCreateDtos")
    User toUser(UserCreateDto source);

    @Mapping(target = "roleCreateDtos", source = "role")
    UserListDto toDto(User source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", source = "roleCreateDtos")
    List<UserListDto> toListDto(List<User> source);
/*
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserLoginDto source);*/

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserCpfDto source);

    @Mapping(target = "role", source = "roleCreateDtos")
    User toUser (UserUpdateDto source);

}






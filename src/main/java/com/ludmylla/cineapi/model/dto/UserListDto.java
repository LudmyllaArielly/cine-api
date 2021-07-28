package com.ludmylla.cineapi.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserListDto {

    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phoneNumber;
    private String city;
    private String state;

    private List<RoleCreateDto> roleCreateDtos;
}

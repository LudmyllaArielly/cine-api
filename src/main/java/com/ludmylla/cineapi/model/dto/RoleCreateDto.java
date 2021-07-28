package com.ludmylla.cineapi.model.dto;

import com.ludmylla.cineapi.model.enums.RoleName;
import lombok.Data;

@Data
public class RoleCreateDto {
    private RoleName name;
}

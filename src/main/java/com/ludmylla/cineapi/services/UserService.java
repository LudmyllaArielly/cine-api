package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.dto.UserLoginDto;

import java.util.List;

public interface UserService {

    String auth(UserLoginDto userLoginDto);

    void create(User user);

    List<User> findAll();
}

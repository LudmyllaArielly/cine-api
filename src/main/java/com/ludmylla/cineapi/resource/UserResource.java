package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.mapper.UserMapper;
import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.dto.UserCreateDto;
import com.ludmylla.cineapi.model.dto.UserListDto;
import com.ludmylla.cineapi.model.dto.UserLoginDto;
import com.ludmylla.cineapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDto userLoginDto){
        try{
            return ResponseEntity.ok(userService.auth(userLoginDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto){
        try {
            User user = UserMapper.INSTANCE.toUser(userCreateDto);
            userService.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserListDto>> findAll(){
        try{
            List<User> user = userService.findAll();
            List<UserListDto> userListDtos = UserMapper.INSTANCE.toListDto(user);
            return ResponseEntity.ok(userListDtos);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

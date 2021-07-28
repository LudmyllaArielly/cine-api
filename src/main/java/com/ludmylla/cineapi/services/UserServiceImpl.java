package com.ludmylla.cineapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludmylla.cineapi.model.Role;
import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.model.dto.UserLoginDto;
import com.ludmylla.cineapi.model.enums.RoleName;
import com.ludmylla.cineapi.repository.RoleRepository;
import com.ludmylla.cineapi.repository.UserRepository;
import com.ludmylla.cineapi.security.TokenProvider;
import com.ludmylla.cineapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements  UserService{

    private AuthenticationManager authenticationManager;

    private TokenProvider tokenProvider;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, TokenProvider tokenProvider,
                           UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String auth(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        Map<String, String> token = new HashMap<>();
        token.put("token", tokenProvider.generateToken(userDetails));
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(token);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(User user) {
        getRoles(user);
        getPasswordToEncrypt(user);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private User getRoles(User user){
        List<Role> rolesAdd = new ArrayList<>();
        for(Role role: user.getRole()){
            if(role.getName().name().equals("ROLE_ADMIN")){
                Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
                rolesAdd.add(roleAdmin);
            }else {
                Role roleUser = roleRepository.findByName(RoleName.ROLE_USER);
                rolesAdd.add(roleUser);
            }
        }
        user.setRole(rolesAdd);
        return user;
    }

    private void getPasswordToEncrypt(User user){
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
    }
}

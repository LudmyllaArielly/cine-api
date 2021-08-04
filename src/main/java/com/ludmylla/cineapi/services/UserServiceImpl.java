package com.ludmylla.cineapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludmylla.cineapi.exceptions.AccessDeniedException;
import com.ludmylla.cineapi.exceptions.UserNotFoundException;
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
            throw new AccessDeniedException("User authentication error.");
        }
    }

    @Override
    public void create(User user) {
        validationCreate(user);
        userRepository.save(user);
    }

    @Override
    public void update(User user){
        validationUpdate(user);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByCpf(String cpf){
        User user = userRepository.findByCpf(cpf);
        validIfUserExists(user);
        return user;
    }

    @Override
    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);
        validIfUserExists(user);
        return user;
    }

    @Override
    public void validUserExist(User user){
        if(user == null){
            throw new UserNotFoundException("User does not exist");
        }
    }

    private void validationCreate(User user){
        getRoles(user);
        getPasswordToEncrypt(user);
    }

    private void validationUpdate(User user){
        findById(user.getId());
        getRoles(user);
        getPasswordToEncrypt(user);
    }

    private User getRoles(User user){
        List<Role> rolesAdd = new ArrayList<>();
        for(Role role: user.getRole()){
            if(role.getName().name().equals("ROLE_ADMIN")){
                Role roleAdmin = getRoleAdmin();
                rolesAdd.add(roleAdmin);
            }else if(role.getName().name().equals("ROLE_USER")) {
                Role roleUser = getRoleUser();
                rolesAdd.add(roleUser);
            }else {
                Role roleUser = getRoleUser();
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

    private void validIfUserExists(User user){
        if(user == null){
            throw new UserNotFoundException("User does not exist.");
        }
    }

    private Role getRoleUser(){
        Role role = roleRepository.findByName(RoleName.ROLE_USER);
        return role;
    }

    private Role getRoleAdmin(){
        Role role = roleRepository.findByName(RoleName.ROLE_ADMIN);
        return role;
    }

    private User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User does not exist."));
    }

}

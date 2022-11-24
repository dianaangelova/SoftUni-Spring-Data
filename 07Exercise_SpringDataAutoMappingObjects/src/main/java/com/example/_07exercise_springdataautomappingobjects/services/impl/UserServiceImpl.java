package com.example._07exercise_springdataautomappingobjects.services.impl;

import com.example._07exercise_springdataautomappingobjects.entities.User;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.CreateUserDto;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.LoginUserDto;
import com.example._07exercise_springdataautomappingobjects.repositories.UserRepository;
import com.example._07exercise_springdataautomappingobjects.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserService userService;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserService userService, UserRepository userRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User registerUser(CreateUserDto createUserDto) {

        //to add administrator

        User user = modelMapper.map(createUserDto, User.class);

        return this.userRepository.save(user);
    }

    @Override
    public User loginUser(LoginUserDto loginUserDto) {
        return null;
    }

    @Override
    public User logoutUser() {
        return null;
    }

    @Override
    public Integer countAll() {
        return this.userService.countAll();
    }

}

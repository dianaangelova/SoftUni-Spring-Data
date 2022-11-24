package com.example._07exercise_springdataautomappingobjects.services;

import com.example._07exercise_springdataautomappingobjects.entities.User;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.CreateUserDto;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.LoginUserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(CreateUserDto createUserDto);

    User loginUser(LoginUserDto loginUserDto);

    User logoutUser();

    Integer countAll();
}

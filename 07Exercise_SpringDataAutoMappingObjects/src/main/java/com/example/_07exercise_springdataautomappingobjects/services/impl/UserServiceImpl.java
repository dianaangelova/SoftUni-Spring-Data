package com.example._07exercise_springdataautomappingobjects.services.impl;

import com.example._07exercise_springdataautomappingobjects.entities.User;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.CreateUserDto;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.LoginUserDto;
import com.example._07exercise_springdataautomappingobjects.repositories.UserRepository;
import com.example._07exercise_springdataautomappingobjects.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private User currentUser;
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = null;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public User registerUser(CreateUserDto createUserDto) {

        User user = modelMapper.map(createUserDto, User.class);

        Optional<User> userExists = this.userRepository.findByEmail(createUserDto.getEmail());

        if (userExists.isPresent()) {
            throw new ValidationException("Email must be unique");
        }

        if (this.userRepository.count() == 0) {
            user.setAdministrator(true);
        }

        return this.userRepository.save(user);
    }

    @Override
    public User loginUser(LoginUserDto loginUserDto) {

        Optional<User> user = this.userRepository.findByEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword());

        if (!user.isPresent()) {
            throw new ValidationException("Incorrect username / password");
        } else {
            return currentUser=user.get();
        }

    }

    @Override
    public User logoutUser() {

        if(currentUser==null){
            throw new ValidationException("Cannot log out. No user was logged in.");
        } else {
            User logoutUser = currentUser;
            currentUser=null;
            return logoutUser;
        }
    }

}

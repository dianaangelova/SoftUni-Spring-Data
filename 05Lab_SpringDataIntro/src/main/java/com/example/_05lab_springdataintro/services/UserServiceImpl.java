package com.example._05lab_springdataintro.services;

import com.example._05lab_springdataintro.models.Account;
import com.example._05lab_springdataintro.models.User;
import com.example._05lab_springdataintro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, int age) {
        // Validate username + age
        // Check username is unique
        // Add default account
        // Save user

        if (username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed");
        }

        Optional<User> byusername = this.userRepository.findByusername(username);
        if (byusername.isPresent()) {
            throw new RuntimeException("username already in use");
        }

        Account account = new Account();
        User user = new User(username, age, account);

        this.userRepository.save(user);
    }

    @Override
    public User findByusername(String username) {
        Optional<User> user =  this.userRepository.findByusername(username);
        return user.get();
    }

//    @Override
//    public User findByusername(String username) {
//        return this.userRepository.findByusername(username).get();    }
}

package com.example._05lab_springdataintro.services;

import com.example._05lab_springdataintro.models.User;

public interface UserService {
    void register(String username, int age);

    User findByusername(String username);
}

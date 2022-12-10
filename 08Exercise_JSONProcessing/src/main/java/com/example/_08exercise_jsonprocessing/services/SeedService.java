package com.example._08exercise_jsonprocessing.services;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public
interface SeedService {

    void seedUsers() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException;

    void seedProducts() throws FileNotFoundException;

    default void seedAll() throws FileNotFoundException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}

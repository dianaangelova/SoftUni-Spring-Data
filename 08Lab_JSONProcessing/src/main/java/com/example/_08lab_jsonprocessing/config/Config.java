package com.example._08lab_jsonprocessing.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public Gson createGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}

package com.example._07exercise_springdataautomappingobjects;

import com.example._07exercise_springdataautomappingobjects.entities.User;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.CreateUserDto;
import com.example._07exercise_springdataautomappingobjects.entities.dtos.LoginUserDto;
import com.example._07exercise_springdataautomappingobjects.services.UserService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private Scanner scanner;


    public ConsoleRunner(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        while(scanner.hasNext()){

            String[] input = scanner.nextLine().split("\\|");
            String output="";
            switch (input[0]) {
                case "RegisterUser":
                    User registerUser = registerUser(input);
                    output = String.format("%s was registered%n", registerUser.getFullName());
                    break;
                case "LoginUser":
                    User loginUser = loginUser(input);
                    output = String.format("Successfully logged in %s%n", loginUser.getFullName());
                    break;
                case "Logout":
                    User logoutUser = logoutUser(input);
                    output = String.format("User %s successfully logged out%n", logoutUser.getFullName());

                    break;
            }
            System.out.println(output);
        }

    }

    private User logoutUser(String[] input) {
        return this.userService.logoutUser();
    }

    private User loginUser(String[] input) {
        String inputEmail = input[1];
        String inputPassword = input[2];

        LoginUserDto loginUserDto = new LoginUserDto(inputEmail, inputPassword);

        return this.userService.loginUser(loginUserDto);
    }

    private User registerUser(String[] input) {

        String inputEmail = input[1];
        String inputPassword = input[2];
        String inputConfirmPassword = input[3];
        String inputFullName = input[4];

        CreateUserDto createUserDto = new CreateUserDto(inputEmail, inputPassword, inputConfirmPassword, inputFullName);
        return this.userService.registerUser(createUserDto);
    }


}

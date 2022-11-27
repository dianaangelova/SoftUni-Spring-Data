package com.example._07exercise_springdataautomappingobjects.entities.dtos;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class CreateUserDto {
    @Valid
    @Email
    private String email;
    @Valid
    @Min(value = 6)
    private String password;
    @Valid
    @Min(value = 6)
    private String confirmPassword;
    private String fullName;

    public CreateUserDto(String email, String password, String confirmPassword, String fullName) {
        validateEmail(email);
        this.email = email;
        validatePassword(password, confirmPassword);
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Password and confirm password must match!");
        }
    }

    public void validateEmail(String email) {
        if (!email.contains("@") || !email.contains(".") || email.indexOf("@") > email.lastIndexOf(".")) {
                throw new ValidationException("Incorrect email.");
        }
    }
}

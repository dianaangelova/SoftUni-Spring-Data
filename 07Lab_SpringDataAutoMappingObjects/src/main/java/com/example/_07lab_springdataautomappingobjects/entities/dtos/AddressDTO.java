package com.example._07lab_springdataautomappingobjects.entities.dtos;

public class AddressDTO {
    private String city;
    private String country;

    public AddressDTO(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }


    public String getCountry() {
        return country;
    }
}

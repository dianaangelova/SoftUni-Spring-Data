package com.example._08lab_jsonprocessing.entities.dtos.addresses;

import com.google.gson.annotations.Expose;

public class CreateAddressDTO {
    @Expose
    private String city;
    @Expose
    private String country;

    public CreateAddressDTO() {
    }

    public CreateAddressDTO(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }


    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

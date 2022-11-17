package com.example._07lab_springdataautomappingobjects.entities;

public class Address {
    private long id;
    private String city;
    private String country;

    private Address(){}

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

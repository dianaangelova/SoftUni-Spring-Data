package com.example._07lab_springdataautomappingobjects.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="addresses")
public class Address {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Address() {

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

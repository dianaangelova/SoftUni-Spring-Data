package com.example._07exercise_springdataautomappingobjects.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany
    private Set<Game> games;

    @Column(nullable = false)
    private boolean isAdministrator;

    @OneToMany (targetEntity = Order.class, mappedBy = "buyer")
    private Set<Order> orders;


    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public User(String email, String password, String fullName) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        this.isAdministrator = administrator;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Game> getGames() {
        return games;
    }

    public Set <Order> getOrders() {
        return orders;
    }
}

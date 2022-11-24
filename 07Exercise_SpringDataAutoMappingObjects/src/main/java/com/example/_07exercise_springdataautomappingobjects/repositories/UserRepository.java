package com.example._07exercise_springdataautomappingobjects.repositories;

import com.example._07exercise_springdataautomappingobjects.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Integer countAll();
}

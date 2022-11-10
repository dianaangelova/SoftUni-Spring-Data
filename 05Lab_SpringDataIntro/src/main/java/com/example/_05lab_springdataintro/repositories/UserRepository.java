package com.example._05lab_springdataintro.repositories;

import com.example._05lab_springdataintro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByusername(String username);
}

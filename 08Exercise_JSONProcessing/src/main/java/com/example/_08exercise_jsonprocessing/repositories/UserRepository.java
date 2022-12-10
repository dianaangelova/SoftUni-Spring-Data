package com.example._08exercise_jsonprocessing.repositories;

import com.example._08exercise_jsonprocessing.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

package com.example._08exercise_jsonprocessing.repositories;

import com.example._08exercise_jsonprocessing.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

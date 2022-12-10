package com.example._08exercise_jsonprocessing.repositories;

import com.example._08exercise_jsonprocessing.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
}

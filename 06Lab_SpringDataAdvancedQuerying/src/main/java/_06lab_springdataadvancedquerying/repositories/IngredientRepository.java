package _06lab_springdataadvancedquerying.repositories;

import _06lab_springdataadvancedquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String name);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);
}

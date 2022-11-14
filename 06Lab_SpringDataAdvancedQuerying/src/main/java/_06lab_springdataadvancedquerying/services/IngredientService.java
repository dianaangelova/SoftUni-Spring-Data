package _06lab_springdataadvancedquerying.services;

import _06lab_springdataadvancedquerying.entities.Ingredient;

import java.util.List;


public interface IngredientService {
    List<Ingredient> findByNameStartingWith(String name);

   List<Ingredient> findByNameInOrderByPrice(List<String> names);


}

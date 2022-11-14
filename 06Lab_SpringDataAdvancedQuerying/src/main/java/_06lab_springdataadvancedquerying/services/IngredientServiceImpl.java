package _06lab_springdataadvancedquerying.services;

import _06lab_springdataadvancedquerying.entities.Ingredient;
import _06lab_springdataadvancedquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findByNameStartingWith(String name) {

        return this.ingredientRepository.findByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> findByNameInOrderByPrice(List<String> names) {

        return this.ingredientRepository.findByNameInOrderByPrice(names);
    }

}

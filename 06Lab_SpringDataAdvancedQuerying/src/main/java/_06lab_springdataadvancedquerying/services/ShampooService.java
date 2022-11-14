package _06lab_springdataadvancedquerying.services;

import _06lab_springdataadvancedquerying.entities.Shampoo;
import _06lab_springdataadvancedquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService{

    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findByBrandAndSize(String brand,  Size size);
    List<Shampoo> findBySizeOrderById(String size);
    List<Shampoo>  findByIngredient(String ingredient);

    List<Shampoo> findByIngredients(List<String> ingredients);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(String size, Long labelID);
}

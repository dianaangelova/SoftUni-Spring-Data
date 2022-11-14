package _06lab_springdataadvancedquerying.services;

import _06lab_springdataadvancedquerying.entities.Shampoo;
import _06lab_springdataadvancedquerying.entities.Size;
import _06lab_springdataadvancedquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, Size size) {
        return this.shampooRepository.findByBrandAndSize(brand, size);
    }

    @Override
    public List<Shampoo> findBySizeOrderById(String size) {
        Size parsed = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findBySizeOrderById(parsed);
    }

    @Override
    public List<Shampoo> findByIngredient(String ingredient) {
        return this.shampooRepository.findByIngredient(ingredient);
    }

    @Override
    public List<Shampoo> findByIngredients(List<String> ingredients) {
        return this.shampooRepository.findByIngredients(ingredients);
    }

    @Override
    public List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(String size, Long labelID) {
        Size parsed = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(parsed, labelID);
    }

}

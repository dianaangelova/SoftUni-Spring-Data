package _05Exercise_springdataintro.services;

import _05Exercise_springdataintro.models.Category;
import _05Exercise_springdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long size = this.categoryRepository.count();

        List<Category> categoriesList = this.categoryRepository.findAll();

        categoriesList.sort(Comparator.comparing(Category::getId));

        Category firstCategory = categoriesList.stream().findFirst().get();

        int firstCategoryID = firstCategory.getId();
        int lastCategoryID = firstCategoryID + (int) size;

        Random rand = new Random();

        int randomCategoryID = (rand.nextInt((int) (lastCategoryID - firstCategoryID)) + firstCategoryID);

        Set<Integer> categoriesIds = new HashSet<>();

        for (int i = 0; i < randomCategoryID; i++) {

            int randomID = (rand.nextInt((int) (lastCategoryID - firstCategoryID)) + firstCategoryID);

            categoriesIds.add(randomID);
        }

        List<Category> categoriesRandomSet = this.categoryRepository.findAllById(categoriesIds);

        return new HashSet<>(categoriesRandomSet);
    }
}

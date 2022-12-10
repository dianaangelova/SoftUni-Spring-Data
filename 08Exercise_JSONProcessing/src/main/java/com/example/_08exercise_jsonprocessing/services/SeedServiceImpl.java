package com.example._08exercise_jsonprocessing.services;

import com.example._08exercise_jsonprocessing.entities.categories.Category;
import com.example._08exercise_jsonprocessing.entities.categories.CategoryImportDTO;
import com.example._08exercise_jsonprocessing.entities.products.Product;
import com.example._08exercise_jsonprocessing.entities.products.ProductImportDTO;
import com.example._08exercise_jsonprocessing.entities.users.User;
import com.example._08exercise_jsonprocessing.entities.users.UserImportDTO;
import com.example._08exercise_jsonprocessing.repositories.CategoryRepository;
import com.example._08exercise_jsonprocessing.repositories.ProductsRepository;
import com.example._08exercise_jsonprocessing.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final Path USERS_JSON_PATH =
            Path.of("src", "main", "resources", "files", "users.json");

    private static final Path CATEGORIES_JSON_PATH =
            Path.of("src", "main", "resources", "files", "categories.json");

    private static final Path PRODUCTS_JSON_PATH =
            Path.of("src", "main", "resources", "files", "products.json");
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductsRepository productsRepository, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USERS_JSON_PATH.toAbsolutePath().toString());
        UserImportDTO[] userImportDTOS = this.gson.fromJson(fileReader, UserImportDTO[].class);

        List<User> users = Arrays.stream(userImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toAbsolutePath().toString());
        CategoryImportDTO[] categoryImportDTOS = this.gson.fromJson(fileReader, CategoryImportDTO[].class);

        List<Category> categories = Arrays.stream(categoryImportDTOS)
                .map(categoryImportDTO -> this.modelMapper.map(categoryImportDTO, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);

    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toAbsolutePath().toString());
        ProductImportDTO[] productImportDTOS = this.gson.fromJson(fileReader, ProductImportDTO[].class);

        List<Product> products = Arrays.stream(productImportDTOS)
                .map(productImportDTO -> this.modelMapper.map(productImportDTO, Product.class))
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .map(this::sendRandomCategories)
                .collect(Collectors.toList());


        this.productsRepository.saveAll(products);

    }

//    private Product setRandomCategories(Product product) {
//        Random random = new Random();
//        long categoriesDBCount = this.categoryRepository.count();
//
//        // избирам колко категории ще имам в дадения продукт
//        int count = random.nextInt((int) (categoriesDBCount));
//
//        Set<Category> categorySet = new HashSet<>();
//
//        while (count > 0) {
//            int randomID = random.nextInt((int) categoriesDBCount) + 1;
//
//            Optional<Category> randomCategory = this.categoryRepository.findById((long) randomID);
//
//            categorySet.add(randomCategory.get());
//            count--;
//        }
//
//        product.setCategories(categorySet);
//        count = 0;
//        return product;
//    }


    private Product sendRandomCategories(Product product) {
        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesDbCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById((long) randomId);

            categories.add(randomCategory.get());
        }

        product.setCategories(categories);
        return product;
    }
    private User setRandomUser() {

        long userCount = this.userRepository.count();

        long randomUserID = new Random().nextInt((int) (userCount) + 1);

        Optional<User> user = Optional.of(this.userRepository.findById(randomUserID).get());

        User user1 = user.get();

        return user1;
    }

    private Product setRandomSeller(Product product) {

        User seller = setRandomUser();

        product.setSeller(seller);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        User buyer = setRandomUser();

        product.setSeller(buyer);

        return product;
    }


}

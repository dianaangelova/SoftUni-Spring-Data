package com.example._08exercise_jsonprocessing;

import com.example._08exercise_jsonprocessing.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductShopApp implements CommandLineRunner {

    private final SeedService seedService;

    @Autowired
    ProductShopApp(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.seedService.seedUsers();
        //this.seedService.seedCategories();
        this.seedService.seedProducts();
    }
}

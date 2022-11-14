package _06lab_springdataadvancedquerying;

import _06lab_springdataadvancedquerying.repositories.IngredientRepository;
import _06lab_springdataadvancedquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {
    private ShampooService shampooService;
    private IngredientRepository ingredientService;

    @Autowired
    public Main(ShampooService shampooService, IngredientRepository ingredientRepository) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // this.shampooService.findByBrand("Fresh it Up!").forEach(shampoo -> System.out.println(shampoo.getBrand()));
        //  this.shampooService.findByBrandAndSize("Cotton Fresh", Size.SMALL).forEach(shampoo -> System.out.println(shampoo.getBrand() + " " +shampoo.getId()));

        Scanner scanner = new Scanner(System.in);

//        01.	Select Shampoos by Size

//        String inputSizeName = scanner.nextLine();
//
//        this.shampooService.findBySizeOrderById(inputSizeName).forEach(shampoo -> System.out.println(shampoo.toString()));


//        02.	Select Shampoos by Size or Label

//        String inputSize = scanner.nextLine();
//        Long inputLabelID = Long.parseLong(scanner.nextLine());
//
//        this.shampooService.findAllBySizeOrLabelIdOrderByPrice(inputSize, inputLabelID).forEach(shampoo -> System.out.println(shampoo.toString()));
//

//        03.	Select Shampoos by Price

//        BigDecimal inputPrice = scanner.nextBigDecimal();
//
//        this.shampooService.findByPriceGreaterThanOrderByPriceDesc(inputPrice).forEach(shampoo -> System.out.println(shampoo.toString()));

//        04.	Select Ingredients by Name

//        String inputString = scanner.nextLine();
//
//        this.ingredientService.findByNameStartingWith(inputString).forEach(ingredient -> System.out.println(ingredient.toString()));


//        05.	Select Ingredients by Names -- not working

        String inputIngredient05 = scanner.nextLine();

        List<String> ingredients05 = new ArrayList<>();

        while (!inputIngredient05.isBlank()) {
            ingredients05.add(inputIngredient05);
            inputIngredient05 = scanner.nextLine();
        }

        this.ingredientService.findByNameInOrderByPrice(ingredients05).forEach(ingredient05 -> System.out.println(ingredient05.toString()));


//        06.	Count Shampoos by Price

//        07.	Select Shampoos by Ingredient

//        String inputIngredient = scanner.nextLine();
//
//        List<String> ingredients = new ArrayList<>();
//
//        while (!inputIngredient.isBlank()) {
//            ingredients.add(inputIngredient);
//            inputIngredient = scanner.nextLine();
//        }
//
//        this.shampooService.findByIngredients(ingredients).forEach(shampoo -> System.out.println(shampoo.toString()));

    }
}

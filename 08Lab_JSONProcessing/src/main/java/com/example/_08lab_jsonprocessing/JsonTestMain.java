package com.example._08lab_jsonprocessing;

import com.example._08lab_jsonprocessing.entities.dtos.addresses.CreateAddressDTO;
import com.example._08lab_jsonprocessing.entities.dtos.CompanyDTO;
import com.example._08lab_jsonprocessing.entities.dtos.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//@Component
public class JsonTestMain implements CommandLineRunner {

    private final Scanner scanner;
    private final Gson gson;

    public JsonTestMain(Scanner scanner) {
        this.scanner = scanner;


    this.gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("YYYY-MM-DD")
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();
    }
    
    class LocalDateAdapter implements JsonSerializer<LocalDate> {
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }


    @Override
    public void run(String... args) throws Exception {

        CreateAddressDTO addressDTO = new CreateAddressDTO("Sofia", "Bulgaria");
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO
                ("First", "Last", BigDecimal.TEN, LocalDate.of(2001, 05, 14), addressDTO);

        CreateAddressDTO addressDTO1 = new CreateAddressDTO("Plovdiv", "Bulgaria");
        CreateEmployeeDTO createEmployeeDTO1 = new CreateEmployeeDTO
                ("Second", "Last", BigDecimal.ONE, LocalDate.of(2002, 05, 14), addressDTO1);

        CreateAddressDTO addressDTO2 = new CreateAddressDTO("Blagoevgrad", "Bulgaria");
        CreateEmployeeDTO createEmployeeDTO2 = new CreateEmployeeDTO
                ("Third", "Last", BigDecimal.ZERO, LocalDate.of(2003, 05, 14), addressDTO2);

        CompanyDTO mega = new CompanyDTO("Mega", List.of(createEmployeeDTO, createEmployeeDTO1, createEmployeeDTO2));

        System.out.println(this.gson.toJson(mega));

         String input = this.scanner.nextLine();

        this.gson.fromJson(input, CompanyDTO.class);
        // Test1();
    }

    private void Test1() {
        CreateAddressDTO addressDTO = new CreateAddressDTO("Sofia", "Bulgaria");
        CreateAddressDTO addressDTO1 = new CreateAddressDTO("Plovdiv", "Bulgaria");
        CreateAddressDTO addressDTO2 = new CreateAddressDTO("Blagoevgrad", "Bulgaria");
        CreateAddressDTO addressDTO3 = new CreateAddressDTO("Burgas", "Bulgaria");

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("First", "Last", BigDecimal.TEN, LocalDate.of(2000, 05, 14), addressDTO1);

        String addressDTOjson = gson.toJson(addressDTO);
        String createEmployeeDTOjson = gson.toJson(createEmployeeDTO);

        //  System.out.println(addressDTOjson);
        System.out.println(createEmployeeDTOjson);

        // List<AddressDTO> addressDTOList = List.of(addressDTO, addressDTO1, addressDTO2, addressDTO3);

        // String input = this.scanner.nextLine();

        // CreateEmployeeDTO parsedDTO = gson.fromJson(input, CreateEmployeeDTO.class);

        // System.out.println(parsedDTO.toString());

        //  System.out.println(gson.toJson(addressDTOList));

        // AddressDTO[] addressDTOSArray = gson.fromJson(input, AddressDTO[].class);

        // Arrays.stream(addressDTOSArray).toList().forEach(System.out::println);
    }
}

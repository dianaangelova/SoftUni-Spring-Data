package com.example._08lab_jsonprocessing;

import com.example._08lab_jsonprocessing.entities.Employee;
import com.example._08lab_jsonprocessing.entities.dtos.addresses.CreateAddressDTO;
import com.example._08lab_jsonprocessing.services.AddressService;
import com.example._08lab_jsonprocessing.entities.dtos.CreateEmployeeDTO;
import com.example._08lab_jsonprocessing.services.EmployeeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {

    private final AddressService addressService;
    private final EmployeeService employeeService;

    private final Scanner scanner;

    private final Gson gson;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService, Scanner scanner, Gson gson) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {

        createAddress();
       // createEmployee();
        //printAllEmployees();
        //printEmployeeNames();
        //printFirstNameAndSalaryById();
    }

    private void printFirstNameAndSalaryById() {
        Long id = scanner.nextLong();
        System.out.println(this.employeeService.findFirstNameAndSalaryById(id));
    }

    private void printEmployeeNames() {
        System.out.println(this.employeeService.findNamesById(1L));
    }

    private void printAllEmployees() {
        this.employeeService.findAll().forEach(System.out::println);
    }

    private void createEmployee() {
        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());
        // напълно валидно е подаване на id за адрес, например надащо меню, тези данни вече сме ги викнали от базата данни
        // long addressId = Long.parseLong(scanner.nextLine());
        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO address = new CreateAddressDTO(country, city);

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.create(createEmployeeDTO);

        System.out.println(employee);
    }

    private void createAddress() {
        String input = this.scanner.nextLine();

        CreateAddressDTO data = this.gson.fromJson(input, CreateAddressDTO.class);

        CreateAddressDTO created = this.addressService.create(data);

        System.out.println(this.gson.toJson(created));
    }
}

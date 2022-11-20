package com.example._07lab_springdataautomappingobjects;

import com.example._07lab_springdataautomappingobjects.entities.Address;
import com.example._07lab_springdataautomappingobjects.entities.Employee;
import com.example._07lab_springdataautomappingobjects.entities.dtos.AddressDTO;
import com.example._07lab_springdataautomappingobjects.services.AddressService;
import com.example._07lab_springdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import com.example._07lab_springdataautomappingobjects.services.EmployeeService;
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

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService, Scanner scanner) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {

        // createAddress(scanner);
        //createEmployee(scanner);
        //printAllEmployees();
        //printEmployeeNames();
        printFirstNameAndSalaryById();
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

        AddressDTO address = new AddressDTO(country, city);

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.create(createEmployeeDTO);

        System.out.println(employee);
    }

    private void createAddress() {
        String country = scanner.nextLine();
        String city = scanner.nextLine();

        //Ако данните идват от външния свят, то няма да ги чета със scanner.nextLine(), a чрез DTO
        AddressDTO data = new AddressDTO(country, city);

        Address address = this.addressService.create(data);

        System.out.println(address);
    }
}

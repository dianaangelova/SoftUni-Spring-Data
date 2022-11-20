package com.example._07lab_springdataautomappingobjects;

import com.example._07lab_springdataautomappingobjects.entities.Address;
import com.example._07lab_springdataautomappingobjects.entities.Employee;
import com.example._07lab_springdataautomappingobjects.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper=new ModelMapper();
//        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
//            @Override
//            protected void configure() {
//                map().setAddressCity(source.getAddress().getCity());
//            }
//        };
//        modelMapper.addMappings(propertyMap);

//        EmployeeDTO employeeDTO = modelMapper.map(employee, com.example._07lab_springdataautomappingobjects.dtos.EmployeeDTO.class);
//        System.out.println(employeeDTO);


        System.out.println("Test");
        Address address = new Address("Sofia", "Bulgaria");
        Employee employee = new Employee("First", BigDecimal.TEN, address);

        TypeMap<Employee, EmployeeDTO> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping-> mapping.map(
                source->source.getAddress().getCity(),
               EmployeeDTO::setAddressCity));
        typeMap.validate();
        EmployeeDTO employeeDTO = typeMap.map(employee);
        System.out.println(employeeDTO);






    }
}

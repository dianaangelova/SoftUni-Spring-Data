package com.example._07lab_springdataautomappingobjects.services;

import com.example._07lab_springdataautomappingobjects.entities.Employee;
import com.example._07lab_springdataautomappingobjects.entities.dtos.CreateEmployeeDTO;
import com.example._07lab_springdataautomappingobjects.entities.dtos.EmployeeDTO;
import com.example._07lab_springdataautomappingobjects.entities.dtos.EmployeeNamesDTO;
import entities.dto.EmployeeAndSalaryDTO;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeNamesDTO findNamesById(long id);

    EmployeeAndSalaryDTO findFirstNameAndSalaryById(long id);
}

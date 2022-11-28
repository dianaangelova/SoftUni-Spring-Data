package com.example._08lab_jsonprocessing.services;

import com.example._08lab_jsonprocessing.entities.Employee;
import com.example._08lab_jsonprocessing.entities.dtos.CreateEmployeeDTO;
import com.example._08lab_jsonprocessing.entities.dtos.EmployeeDTO;
import com.example._08lab_jsonprocessing.entities.dtos.EmployeeNamesDTO;
import entities.dto.EmployeeAndSalaryDTO;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeNamesDTO findNamesById(long id);

    EmployeeAndSalaryDTO findFirstNameAndSalaryById(long id);
}

package com.example._07lab_springdataautomappingobjects.repositories;

import com.example._07lab_springdataautomappingobjects.entities.Employee;
import com.example._07lab_springdataautomappingobjects.entities.dtos.EmployeeNamesDTO;
import entities.dto.EmployeeAndSalaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT new com.example._07lab_springdataautomappingobjects.entities.dtos.EmployeeNamesDTO(e.firstName, e.lastName) " +
            " FROM Employee e " +
            " WHERE e.id = :id")
    EmployeeNamesDTO findNamesById(long id);

    EmployeeAndSalaryDTO findFirstNameAndSalaryById(long id);
}

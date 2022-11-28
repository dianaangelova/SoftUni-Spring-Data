package com.example._08lab_jsonprocessing.services;

import com.example._08lab_jsonprocessing.entities.Address;
import com.example._08lab_jsonprocessing.entities.Employee;
import com.example._08lab_jsonprocessing.entities.dtos.CreateEmployeeDTO;
import com.example._08lab_jsonprocessing.entities.dtos.EmployeeDTO;
import com.example._08lab_jsonprocessing.entities.dtos.EmployeeNamesDTO;
import com.example._08lab_jsonprocessing.repositories.AddressRepository;
import com.example._08lab_jsonprocessing.repositories.EmployeeRepository;
import entities.dto.EmployeeAndSalaryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee create(CreateEmployeeDTO employeeDTO) {

        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Optional<Address> address = this.addressRepository.findByCountryAndCity(employee.getAddress().getCountry(), employee.getAddress().getCity());

        if (address.isPresent()) {
            employee.setAddress(address.get());
        }
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return this.employeeRepository
                .findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeNamesDTO findNamesById(long id) {
        return this.employeeRepository.findNamesById(id);
    }

    @Override
    public EmployeeAndSalaryDTO findFirstNameAndSalaryById(long id) {
        return this.employeeRepository.findFirstNameAndSalaryById(id);
    }
}

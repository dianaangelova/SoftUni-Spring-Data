package com.example._07lab_springdataautomappingobjects.services;

import com.example._07lab_springdataautomappingobjects.entities.Address;
import com.example._07lab_springdataautomappingobjects.entities.dtos.AddressDTO;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    Address create(AddressDTO data);
}

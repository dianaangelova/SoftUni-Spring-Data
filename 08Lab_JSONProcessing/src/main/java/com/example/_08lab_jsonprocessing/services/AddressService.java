package com.example._08lab_jsonprocessing.services;

import com.example._08lab_jsonprocessing.entities.dtos.addresses.AddressDTO;
import com.example._08lab_jsonprocessing.entities.dtos.addresses.CreateAddressDTO;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    AddressDTO create(CreateAddressDTO data);
}

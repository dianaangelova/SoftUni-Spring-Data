package com.example._08lab_jsonprocessing.services;

import com.example._08lab_jsonprocessing.entities.Address;
import com.example._08lab_jsonprocessing.entities.dtos.addresses.AddressDTO;
import com.example._08lab_jsonprocessing.entities.dtos.addresses.CreateAddressDTO;
import com.example._08lab_jsonprocessing.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    private final ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressDTO create(CreateAddressDTO data) {

        Address address = mapper.map(data, Address.class);

        Address saved = this.addressRepository.save(address);

        return this.mapper.map(saved, AddressDTO.class);
    }
}

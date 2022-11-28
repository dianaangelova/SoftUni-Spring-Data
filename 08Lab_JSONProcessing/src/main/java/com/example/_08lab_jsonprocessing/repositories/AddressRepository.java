package com.example._08lab_jsonprocessing.repositories;

import com.example._08lab_jsonprocessing.entities.Address;
import com.example._08lab_jsonprocessing.entities.dtos.addresses.CreateAddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCountryAndCity(String country, String city);
}

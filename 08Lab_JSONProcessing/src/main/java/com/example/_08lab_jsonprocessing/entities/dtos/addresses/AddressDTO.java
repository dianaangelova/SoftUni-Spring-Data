package com.example._08lab_jsonprocessing.entities.dtos.addresses;

import com.google.gson.annotations.Expose;

public class AddressDTO extends CreateAddressDTO{

    @Expose
    private Long id;

    public AddressDTO(){
        super();
    }

    public AddressDTO(String city, String country) {
        super(city, country);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

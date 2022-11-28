package com.example._08lab_jsonprocessing.entities.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CompanyDTO {

    @Expose
    String name;

    @Expose
    List<CreateEmployeeDTO> list;

    public CompanyDTO(String name, List<CreateEmployeeDTO> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateEmployeeDTO> getList() {
        return list;
    }

    public void setList(List<CreateEmployeeDTO> list) {
        this.list = list;
    }
}

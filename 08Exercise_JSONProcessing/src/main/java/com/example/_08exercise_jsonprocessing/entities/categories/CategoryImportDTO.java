package com.example._08exercise_jsonprocessing.entities.categories;

import com.google.gson.annotations.Expose;

public class CategoryImportDTO {

    @Expose
    private String name;

    public CategoryImportDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

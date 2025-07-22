package com.hb.cda.examrest.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGroupDTO {

    @NotBlank
    @Size(min=4, max=80)
    private String name;
    @Size(max=200)
    private String description;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    
}

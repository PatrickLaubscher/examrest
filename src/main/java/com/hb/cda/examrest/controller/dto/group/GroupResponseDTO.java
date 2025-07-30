package com.hb.cda.examrest.controller.dto.group;

public class GroupResponseDTO {

    private String id;
    private String name;
    private int number;
    private String description;
    public GroupResponseDTO(String id, String name, int number, String description) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    } 
    
}

package com.hb.cda.examrest.controller.dto.user;

import java.util.List;

import com.hb.cda.examrest.controller.dto.group.GroupDTO;

public class UserDTO {

    private String firstname;
    private String lastname;
    private String email;
    private List<GroupDTO> groups;
    
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<GroupDTO> getGroups() {
        return groups;
    }
    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }
    
}

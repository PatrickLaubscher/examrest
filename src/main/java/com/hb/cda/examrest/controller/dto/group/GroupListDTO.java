package com.hb.cda.examrest.controller.dto.group;

import java.util.List;

public class GroupListDTO {

    private List<GroupDTO> groups;

    public GroupListDTO(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public List<GroupDTO> getgroups() {
        return groups;
    }

    public void setgroups(List<GroupDTO> groups) {
        this.groups = groups;
    }
    
}

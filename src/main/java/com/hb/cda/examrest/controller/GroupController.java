package com.hb.cda.examrest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.GroupBusiness;
import com.hb.cda.examrest.controller.dto.group.CreateGroupDTO;
import com.hb.cda.examrest.controller.dto.group.GroupDTO;
import com.hb.cda.examrest.controller.dto.group.GroupListDTO;
import com.hb.cda.examrest.controller.dto.group.GroupMapper;
import com.hb.cda.examrest.controller.dto.group.GroupResponseDTO;
import com.hb.cda.examrest.model.Group;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupBusiness groupBusiness;
    private final GroupMapper groupMapper;


    public GroupController(GroupBusiness groupBusiness, GroupMapper groupMapper) {
        this.groupBusiness = groupBusiness;
        this.groupMapper = groupMapper;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupResponseDTO createGroup(@RequestBody @Valid CreateGroupDTO dto) {
        Group newGroup = groupBusiness.createGroup(groupMapper.toEntity(dto));
        return groupMapper.toResponseDTO(newGroup);
    } 

    @GetMapping("")
    public GroupDTO getGroupById(@RequestParam int groupNumber) {
        return groupMapper.toDTO(groupBusiness.findGroup(groupNumber));
    }

    @GetMapping("/all-group")
    public GroupListDTO getAllGroup(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        List<Group> groups = groupBusiness.findAllGroupByUser(email);
        return groupMapper.toGroupListDTO(groups);
    }



    
}

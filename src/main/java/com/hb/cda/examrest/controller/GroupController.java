package com.hb.cda.examrest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.GroupBusiness;
import com.hb.cda.examrest.controller.dto.group.CreateGroupDTO;
import com.hb.cda.examrest.controller.dto.group.GroupDTO;
import com.hb.cda.examrest.controller.dto.group.GroupListDTO;
import com.hb.cda.examrest.controller.dto.group.GroupMapper;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.security.jwt.JwtUtil;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupBusiness groupBusiness;
    private final GroupMapper groupMapper;
    private final JwtUtil jwtUtil;


    public GroupController(GroupBusiness groupBusiness, GroupMapper groupMapper, JwtUtil jwtUtil) {
        this.groupBusiness = groupBusiness;
        this.groupMapper = groupMapper;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public String createGroup(@RequestBody @Valid CreateGroupDTO dto) {
        Group newGroup = groupBusiness.createGroup(groupMapper.toEntity(dto));
            return "Groupe n°" + newGroup.getNumber() + " créé";
    } 

    @GetMapping("")
    public GroupDTO getGroupById(@RequestParam int groupNumber) {
        return groupMapper.toDTO(groupBusiness.findGroup(groupNumber));
    }

    @GetMapping("/all-group")
    public GroupListDTO getAllGroup(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        List<Group> groups = groupBusiness.findAllGroupByUser(email);
        return groupMapper.toGroupListDTO(groups);
    }



    
}

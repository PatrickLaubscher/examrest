package com.hb.cda.examrest.controller.dto.group;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.hb.cda.examrest.model.Group;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {

    Group toEntity(CreateGroupDTO dto);
    GroupDTO toDTO(Group group);
    
}

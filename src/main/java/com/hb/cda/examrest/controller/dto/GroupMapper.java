package com.hb.cda.examrest.controller.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.hb.cda.examrest.model.Group;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {

    Group toEntity(CreateGroupDTO dto);
    
}

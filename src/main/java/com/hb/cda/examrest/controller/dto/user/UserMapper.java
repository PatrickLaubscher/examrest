package com.hb.cda.examrest.controller.dto.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.hb.cda.examrest.model.User;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(CreateUserDTO dto);
    UserDTO toDTO(User user);
}

package com.hb.cda.examrest.controller.dto.group;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.hb.cda.examrest.model.Group;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {

    Group toEntity(CreateGroupDTO dto);
    GroupDTO toDTO(Group group);

    List<GroupDTO> toDTOList(List<Group> groups);

    default GroupListDTO toGroupListDTO(List<Group> groups) {
        List<GroupDTO> dtoList = toDTOList(groups);
        return new GroupListDTO(dtoList);
    }
    
}

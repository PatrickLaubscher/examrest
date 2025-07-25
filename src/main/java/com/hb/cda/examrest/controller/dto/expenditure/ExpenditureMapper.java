package com.hb.cda.examrest.controller.dto.expenditure;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.hb.cda.examrest.model.Expenditure;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpenditureMapper {
    
    ExpenditureDTO toDTO(Expenditure expenditure);
    List<ExpenditureDTO> toDTOList(List<Expenditure> expenditures);

    default ExpenditureListDTO toExpenditureListDTO(List<Expenditure> expenditures) {
        List<ExpenditureDTO> dtoList = toDTOList(expenditures);
        return new ExpenditureListDTO(dtoList);
    }

}

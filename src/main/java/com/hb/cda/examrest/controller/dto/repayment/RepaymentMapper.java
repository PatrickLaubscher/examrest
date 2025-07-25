package com.hb.cda.examrest.controller.dto.repayment;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.hb.cda.examrest.model.Repayment;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepaymentMapper {

    RepaymentDTO toDTO(Repayment repayment);
    List<RepaymentDTO> toDTOList(List<Repayment> repayments);

    default RepaymentListDTO toRepaymentListDTO(List<Repayment> repayments) {
        List<RepaymentDTO> dtoList = toDTOList(repayments);
        return new RepaymentListDTO(dtoList);
    }

}

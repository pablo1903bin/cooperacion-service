package com.tesoramobil.cooperacion.mappers;
import org.mapstruct.Mapper;


import com.tesoramobil.cooperacion.dtos.CooperacionResumeDto;
import com.tesoramobil.cooperacion.entities.CooperationEntity;


@Mapper(componentModel = "spring")
public interface CooperationMapper {
	

    CooperacionResumeDto toDto(CooperationEntity entity);


    CooperationEntity toEntity(CooperacionResumeDto dto);
    
}

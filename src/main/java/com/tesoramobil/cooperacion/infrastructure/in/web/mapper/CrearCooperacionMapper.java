package com.tesoramobil.cooperacion.infrastructure.in.web.mapper;
import org.mapstruct.Mapper;

import com.tesoramobil.cooperacion.application.comand.CrearCooperacionCommand;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.CrearCooperacionRequest;


@Mapper(componentModel = "spring")
public interface CrearCooperacionMapper {
    CrearCooperacionCommand toCommand(CrearCooperacionRequest request);
}
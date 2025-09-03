package com.tesoramobil.cooperacion.infrastructure.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionResumenResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CrearCooperacionRequest;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CooperacionWebMapper {

    // request -> dominio
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "ACTIVA") // <- antes estaba ignore
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "actualizadoEn", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "actualizadoPor", source = "creadoPor")
    @Mapping(target = "montoActual", expression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "montoRestante", source = "montoObjetivo")
    Cooperacion toDomain(CrearCooperacionRequest req);

    // dominio -> response (si los nombres coinciden, se mapea solo)
    CooperacionResponse toResponse(Cooperacion domain);
    
    
    CooperacionResumenResponse toResumen(Cooperacion domain);
    
    
    List<CooperacionResumenResponse> toResumenList(List<Cooperacion> list);
    
    
}

package com.tesoramobil.cooperacion.mappers;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.dtos.CooperacionConAportacionesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CooperacionDetalleMapper {

    // Ignoramos 'aportaciones' aquí; la setea el servicio
    @Mapping(target = "aportaciones", ignore = true)
    CooperacionConAportacionesDTO toDetalle(Cooperacion domain);
}
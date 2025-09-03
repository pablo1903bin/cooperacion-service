package com.tesoramobil.cooperacion.infrastructure.persistence.jpa.mapper;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.infrastructure.persistence.jpa.entity.CooperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;


@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR // si algo no se mapea, truena al compilar
)
public interface CooperacionMapper {

    // ===== Entity -> Dominio =====
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "groupId",   target = "grupoId")
    @Mapping(source = "updateAt",  target = "actualizadoEn")
    @Mapping(source = "updateBy",  target = "actualizadoPor")
    Cooperacion toDomain(CooperationEntity entity);

    // ===== Dominio -> Entity =====
    @Mapping(source = "creadoPor",      target = "createdBy")
    @Mapping(source = "grupoId",        target = "groupId")
    @Mapping(source = "actualizadoEn",  target = "updateAt")
    @Mapping(source = "actualizadoPor", target = "updateBy")
    @Mapping(target = "aportaciones", ignore = true) // este lo ignoramos por ahora
    CooperationEntity toEntity(Cooperacion domain);

    List<Cooperacion> toDomainList(List<CooperationEntity> entities);
}